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
 * Created by boebel on 04.01.2017.
 */
const core_1 = require("@angular/core");
const http_1 = require("@angular/http");
const Observable_1 = require("rxjs/Observable");
require("rxjs/Rx");
let HomeService = class HomeService {
    constructor(http) {
        this.http = http;
        this.http = http;
    }
    getCompany() {
        let data;
        return this.http.get('http://localhost:8080/rest/companies')
            .map(response => response.json());
    }
    getTime() {
        return Observable_1.Observable.interval(1000).flatMap(() => this.http.get('http://localhost:8080/rest/time').map(response => response.text()));
    }
    isBankrupt() {
        return Observable_1.Observable.interval(1000).flatMap(() => this.http.get('http://localhost:8080/rest/companies/bankrupt')).map(response => response.text());
    }
    getToken() {
        return window.localStorage.getItem("auth_key");
    }
    validateToken() {
        var headers = new http_1.Headers();
        console.log(this.getToken());
        if (this.getToken())
            headers.append("Authorization", "Bearer " + this.getToken());
        else
            return null;
        return this.http.get('http://localhost:8080/rest/auth', { headers })
            .map(response => response.text());
    }
    logOut() {
        return this.http.put("http://localhost:8080/rest/logout", null).map(res => res.text());
    }
    getMarktanteile() {
        return this.http.get("http://localhost:8080/rest/players").map(res => res.json());
    }
    getHighscore() {
        return this.http.get("http://localhost:8080/rest/highscore").map(res => res.json());
    }
    getNoZuschlagError() {
        return this.http.get("http://localhost:8080/rest/companies/zuschlagerror").map(res => res.text());
    }
};
HomeService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], HomeService);
exports.HomeService = HomeService;
//# sourceMappingURL=home.service.js.map