/**
 * Created by boebel on 12.01.2017.
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
const forms_1 = require("@angular/forms");
const hr_service_1 = require("../../../services/hr.service");
const min_wage_directive_1 = require("../../../directives/min-wage.directive");
let NewEmployeeComponent = class NewEmployeeComponent {
    constructor(_hrService, fb) {
        this._hrService = _hrService;
        this.fb = fb;
        this.newEmployee = fb.group({
            'anzahl': [null, forms_1.Validators.compose([forms_1.Validators.required, forms_1.Validators.pattern("[0-9]+")])],
            'gehalt': [null, forms_1.Validators.compose([forms_1.Validators.required, forms_1.Validators.pattern("[0-9]+"), min_wage_directive_1.maximumWageValidator(1360)])],
            'abteilung': [null, forms_1.Validators.required]
        });
    }
    submitForm(value) {
        this._hrService.addEmployees({ anzahl: value.anzahl, gehalt: value.gehalt, abteilung: value.abteilung });
    }
};
NewEmployeeComponent = __decorate([
    core_1.Component({
        selector: 'new-employee-modal',
        templateUrl: '../../../../templates/components/home.components/human-ressources.component/new-employee.component.html',
    }),
    __metadata("design:paramtypes", [hr_service_1.HRService, forms_1.FormBuilder])
], NewEmployeeComponent);
exports.NewEmployeeComponent = NewEmployeeComponent;
//# sourceMappingURL=new-employee.component.js.map