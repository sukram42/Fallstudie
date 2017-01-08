"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require("@angular/core");
var login_formular_service_1 = require("../services/login-formular.service");
var LoginFormComponent = (function () {
    function LoginFormComponent(_logFormService) {
        this._logFormService = _logFormService;
        this.creds = { username: "",
            password: "" };
    }
    LoginFormComponent.prototype.anmeldenClicked = function () {
        var _this = this;
        this._logFormService.requestToken(this.creds.username, this.creds.password)
            .subscribe(function (data) { return window.localStorage.setItem("auth_key", data); }, function (err) { return _this.error = true; });
        console.log(window.localStorage.getItem("auth_key"));
        if (!this.error) {
            window.location.href = "home.html";
        }
    };
    return LoginFormComponent;
}());
LoginFormComponent = __decorate([
    core_1.Component({
        selector: 'login-formular',
        templateUrl: '../../../../templates/components/login-formular.component.html',
        providers: [login_formular_service_1.LoginFormularService]
    })
], LoginFormComponent);
exports.LoginFormComponent = LoginFormComponent;
