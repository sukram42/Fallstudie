/**
 * Created by boebel on 13.01.2017.
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
const Subject_1 = require("rxjs/Subject");
let HRService = class HRService {
    constructor(http) {
        this.http = http;
        this.empSubject = new Subject_1.Subject();
        this.socialProjectsSubject = new Subject_1.Subject();
    }
    addEmployees(values) {
        return this.http.post('http://localhost:8080/rest/companies/employees', values)
            .map(res => res.text())
            .subscribe(data => {
            if (data.toString().startsWith("ERROR"))
                this.empSubject.next(data);
            else
                this.empSubject.next("neuer Mitarbeiter im Lande");
        });
    }
    getEmployees() {
        return this.http.get('http://localhost:8080/rest/companies/employees').map(res => res.json());
    }
    getEmployeesProduktion() {
        return this.http.get('http://localhost:8080/rest/companies/employees/production').map(res => res.text());
    }
    getEmployeesMarketing() {
        return this.http.get('http://localhost:8080/rest/companies/employees/marketing').map(res => res.text());
    }
    getEmployeesHR() {
        return this.http.get('http://localhost:8080/rest/companies/employees/hr').map(res => res.text());
    }
    getEmployeesSales() {
        return this.http.get('http://localhost:8080/rest/companies/employees/sales').map(res => res.text());
    }
    getEmployeesResearch() {
        return this.http.get('http://localhost:8080/rest/companies/employees/research').map(res => res.text());
    }
    getEmployeeSubject() {
        return this.empSubject.asObservable();
    }
    getSocialProjectSubject() {
        return this.socialProjectsSubject.asObservable();
    }
    getSocialProject() {
        return this.http.get('http://localhost:8080/rest/companies/employees/socialprojects').map(res => res.json());
    }
    changeProjectActivity(name) {
        return this.http.put('http://localhost:8080/rest/companies/employees/socialprojects/', name).map(res => res.text())
            .subscribe(data => { }, err => { console.log(err); }, () => this.socialProjectsSubject.next(name));
    }
    fire(opfer) {
        ;
        var data = JSON.stringify(opfer);
        this.http.put('http://localhost:8080/rest/companies/employees', data).map(res => res.text())
            .subscribe(data => this.empSubject.next("Opfer detected and fired"), err => this.empSubject.error(err));
    }
};
HRService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], HRService);
exports.HRService = HRService;
//# sourceMappingURL=hr.service.js.map