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
const login_formular_service_1 = require("../services/login-formular.service");
let LoginFormComponent = class LoginFormComponent {
    constructor(_logFormService) {
        this._logFormService = _logFormService;
        this.creds = { username: "",
            password: "" };
    }
    anmeldenClicked() {
        this._logFormService.requestToken(this.creds.username, this.creds.password);
    }
};
LoginFormComponent = __decorate([
    core_1.Component({
        selector: 'login-formular',
        templateUrl: '../../../../templates/components/login-formular.component.html',
        providers: [login_formular_service_1.LoginFormularService]
    }),
    __metadata("design:paramtypes", [login_formular_service_1.LoginFormularService])
], LoginFormComponent);
exports.LoginFormComponent = LoginFormComponent;
//# sourceMappingURL=login-formular.component.js.map