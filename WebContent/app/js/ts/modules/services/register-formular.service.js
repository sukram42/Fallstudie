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
require("rxjs/add/operator/map");
const login_formular_service_1 = require("./login-formular.service");
let RegisterFormularService = class RegisterFormularService {
    constructor(http, logInService) {
        this.http = http;
        this.logInService = logInService;
    }
    createCompany(companyname, password) {
        var params = { name: companyname, passwort: password };
        var headers = new http_1.Headers();
        // headers.append('Content-Type','application/x-www-form-urlencoded');
        return this.http.post('http://localhost:8080/rest/companies', params, { headers })
            .map(response => response.text());
    }
};
RegisterFormularService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http, login_formular_service_1.LoginFormularService])
], RegisterFormularService);
exports.RegisterFormularService = RegisterFormularService;
//# sourceMappingURL=register-formular.service.js.map