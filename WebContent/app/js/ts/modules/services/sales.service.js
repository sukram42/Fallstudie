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
const http_1 = require("@angular/http");
const Subject_1 = require("rxjs/Subject");
const core_1 = require("@angular/core");
/**
 * Created by boebel on 24.01.2017.
 */
let SalesService = class SalesService {
    constructor(http) {
        this.http = http;
        this.opportunitiesSubject = new Subject_1.Subject();
        this.creditSubject = new Subject_1.Subject();
    }
    getAusschreibungen() {
        //return Observable.interval(1000).flatMap(()=>this.http.get('http://localhost:8080/rest/companies/sales/ausschreibungen').map(response => response.json()));
        return this.http.get('http://localhost:8080/rest/companies/sales/ausschreibungen').map(response => response.json());
    }
    getOpportunities() {
        // return Observable.interval(1000).flatMap(()=>this.http.get('http://localhost:8080/rest/ausschreibungen').map(response => response.json()));
        return this.http.get('http://localhost:8080/rest/companies/sales/opportunities').map(response => response.json());
    }
    getOpportunitiesSubject() {
        return this.opportunitiesSubject.asObservable();
    }
    bewerben(index) {
        return this.http.post('http://localhost:8080/rest/companies/sales/ausschreibungen', index)
            .map(response => response.text())
            .subscribe(data => this.opportunitiesSubject.next(data));
    }
    getAccounts() {
        // return Observable.interval(1000).flatMap(()=>this.http.get('http://localhost:8080/rest/ausschreibungen').map(response => response.json()));
        return this.http.get('http://localhost:8080/rest/companies/sales/accounts').map(response => response.json());
    }
    requestKredit(values) {
        return this.http.post('http://localhost:8080/rest/companies/sales/credits', values)
            .map(response => response.text())
            .subscribe(data => { this.creditSubject.next(data), console.log(data); });
    }
    getAusbringung() {
        return this.http.get('http://localhost:8080/rest/companies/sales/volume').map(res => res.json());
    }
    getBestand() {
        return this.http.get('http://localhost:8080/rest/companies/sales/durations').map(res => res.json());
    }
    getCredits() {
        return this.http.get('http://localhost:8080/rest/companies/sales/credits')
            .map(response => response.json());
    }
    getCreditSubject() {
        return this.creditSubject.asObservable();
    }
};
SalesService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], SalesService);
exports.SalesService = SalesService;
//# sourceMappingURL=sales.service.js.map