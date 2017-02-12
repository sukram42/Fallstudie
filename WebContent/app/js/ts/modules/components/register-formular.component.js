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
const register_formular_service_1 = require("../services/register-formular.service");
const login_formular_service_1 = require("../services/login-formular.service");
let RegisterFormularComponent = class RegisterFormularComponent {
    constructor(regService, logInService) {
        this.regService = regService;
        this.logInService = logInService;
        this.passworderror = false;
    }
    onRegisterClicked() {
        if (this.input_password == this.input_password2 && this.input_companyName != "") {
            this.regService.createCompany(this.input_companyName, this.input_password)
                .subscribe(data => this.logIn(this.input_companyName, this.input_password), err => console.log("ERR" + err));
        }
        else
            this.passworderror = true;
    }
    logIn(companyname, password) {
        this.logInService.requestToken(companyname, password);
    }
};
RegisterFormularComponent = __decorate([
    core_1.Component({
        selector: 'register-formular',
        templateUrl: '../../../../templates/components/register-formular.component.html',
        providers: [register_formular_service_1.RegisterFormularService, login_formular_service_1.LoginFormularService]
    }),
    __metadata("design:paramtypes", [register_formular_service_1.RegisterFormularService, login_formular_service_1.LoginFormularService])
], RegisterFormularComponent);
exports.RegisterFormularComponent = RegisterFormularComponent;
//# sourceMappingURL=register-formular.component.js.map