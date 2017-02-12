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
const Observable_1 = require("rxjs/Observable");
require("rxjs/Rx");
let KeyFiguresService = class KeyFiguresService {
    constructor(_http) {
        this._http = _http;
        this._http = _http;
    }
    getSoftKeyFigure(name) {
        return Observable_1.Observable.interval(10000).flatMap(() => this._http.get('http://localhost:8080/rest/companies/keyfigures/soft/' + name)
            .map(response => response.json()));
    }
    getSoftKeyFigures() {
        return Observable_1.Observable.interval(1000).flatMap(() => this._http.get('http://localhost:8080/rest/companies/keyfigures/soft')
            .map(response => response.json()));
    }
    getKeyFigures() {
        return this._http.get('http://localhost:8080/rest/companies/keyfigures').map(res => res.json());
    }
    getBilanz() {
        return this._http.get('http://localhost:8080/rest/companies/keyfigures/bilanz').map(res => res.json());
    }
    getEmployeeCount() {
        return Observable_1.Observable.interval(5000).flatMap(() => this._http.get('http://localhost:8080/rest/companies/employees/count')
            .map(response => response.text()));
    }
    getLiquideMittel() {
        return Observable_1.Observable.interval(1000).flatMap(() => this._http.get('http://localhost:8080/rest/companies/keyfigures/bilanz')
            .map(res => res.json()));
    }
    getArchivKennzahlen() {
        return Observable_1.Observable.interval(1000).flatMap(() => this._http.get('http://localhost:8080/rest/companies/keyfigures/archiv')
            .map(res => res.json()));
    }
};
KeyFiguresService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], KeyFiguresService);
exports.KeyFiguresService = KeyFiguresService;
//# sourceMappingURL=keyfigures.service.js.map