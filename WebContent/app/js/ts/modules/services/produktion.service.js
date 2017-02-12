/**
 * Created by boebel on 18.01.2017.
 */
"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
const core_1 = require("@angular/core");
const http_1 = require("@angular/http");
require("rxjs/Rx");
const Subject_1 = require("rxjs/Subject");
const Observable_1 = require("rxjs/Observable");
let ProduktionService = class ProduktionService {
    constructor(http) {
        this.http = http;
        this.productlinesSubject = new Subject_1.Subject();
        this.machinesSubject = new Subject_1.Subject();
        this.warehouseSubject = new Subject_1.Subject();
        this.http = http;
    }
    produzieren(data) {
        this.http.post('http://localhost:8080/rest/companies/production', data)
            .map(res => res.text())
            .subscribe(data => {
            if (data.toString().startsWith("ERROR"))
                this.productlinesSubject.next("ERROR" + 2);
            else
                this.productlinesSubject.next("new Product");
        }, err => console.log(err));
    }
    getProductlinesSubject() {
        return this.productlinesSubject.asObservable();
    }
    getMachinesSubject() {
        return this.machinesSubject;
    }
    getWarehouseSubject() {
        return this.warehouseSubject;
    }
    kaufeMaschine(data) {
        return this.http.post('http://localhost:8080/rest/companies/production/machines', data)
            .map(res => res.text())
            .subscribe(data => {
            if (data.toString().startsWith("ERROR"))
                this.machinesSubject.next("ERROR" + 1);
            else
                this.machinesSubject.next("Neue Maschine");
        });
    }
    kaufeLager(size) {
        return this.http.post('http://localhost:8080/rest/companies/production/warehouses', size).map(res => res.text());
    }
    getLagerkapazitaet() {
        return this.http.get('http://localhost:8080/rest/companies/production/warehouses/capacities').map(res => res.json());
    }
    getLagerkapazitaetIntervall() {
        return Observable_1.Observable.interval(5000).flatMap(() => this.http.get('http://localhost:8080/rest/companies/production/warehouses/capacities').map(res => res.json()));
    }
    kaufeProduktionshalle(size) {
        return this.http.post('http://localhost:8080/rest/companies/production/halls', size).map(res => res.text());
    }
    getProduktionshallenkapazitaet() {
        return this.http.get('http://localhost:8080/rest/companies/production/halls/capacities').map(res => res.json());
    }
    getProduktlinien() {
        return this.http.get('http://localhost:8080/rest/companies/production/productlines').map(res => res.json());
    }
    getMachines() {
        return this.http.get('http://localhost:8080/rest/companies/production/machines').map(res => res.json());
    }
    repairMachines(index) {
        return this.http.put('http://localhost:8080/rest/companies/production/machines', index).map(res => res.text());
    }
    getMachinesStatus(no) {
        return this.http.get('http://localhost:8080/rest/companies/production/machines/' + no + '/status').map(res => res.text());
    }
    sell(index) {
        return this.http.delete('http://localhost:8080/rest/companies/production/machines/' + index).map(res => res.text()).subscribe((data) => this.machinesSubject.next(data), err => console.log(err));
    }
    getStaticMachinesEnergyCosts() {
        return this.http.get('http://localhost:8080/rest/companies/production/machines/energy').map(res => res.text());
    }
};
ProduktionService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], ProduktionService);
exports.ProduktionService = ProduktionService;
//# sourceMappingURL=produktion.service.js.map