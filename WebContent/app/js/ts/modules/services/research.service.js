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
/**
 * Created by boebel on 10.02.2017.
 */
const http_1 = require("@angular/http");
const Subject_1 = require("rxjs/Subject");
const core_1 = require("@angular/core");
let ResearchService = class ResearchService {
    constructor(_http) {
        this._http = _http;
        this.researchSubject = new Subject_1.Subject();
    }
    starteProjekt(value) {
        this._http.post('http://localhost:8080/rest/companies/research/projects', value)
            .map(res => res.text())
            .subscribe(data => this.researchSubject.next(data));
    }
    getResearchSubject() {
        return this.researchSubject.asObservable();
    }
    getProjects() {
        return this._http.get('http://localhost:8080/rest/companies/research/projects')
            .map(res => res.json());
    }
    getOldProjects() {
        return this._http.get('http://localhost:8080/rest/companies/research/projects/finished')
            .map(res => res.json());
    }
};
ResearchService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], ResearchService);
exports.ResearchService = ResearchService;
//# sourceMappingURL=research.service.js.map