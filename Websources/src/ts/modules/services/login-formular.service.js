"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
/**
 * Created by boebel on 04.01.2017.
 */
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
require("rxjs/add/operator/map");
var LoginFormularService = (function () {
    function LoginFormularService(http) {
        this.http = http;
    }
    LoginFormularService.prototype.requestToken = function (username, password) {
        var params = "username=" + username + "&password=" + password;
        var headers = new http_1.Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        return this.http.post('http://localhost:8080/Fallstudie-0.0.1-SNAPSHOT/rest', params, { headers: headers })
            .map(function (response) { return response.text(); });
    };
    return LoginFormularService;
}());
LoginFormularService = __decorate([
    core_1.Injectable()
], LoginFormularService);
exports.LoginFormularService = LoginFormularService;
