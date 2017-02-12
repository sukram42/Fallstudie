/**
 * Created by boebel on 04.02.2017.
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
const sales_service_1 = require("../../../services/sales.service");
let NewKreditComponent = class NewKreditComponent {
    constructor(_financeService, fb) {
        this._financeService = _financeService;
        this.fb = fb;
        this.newKredit = fb.group({
            'betrag': [null, forms_1.Validators.compose([forms_1.Validators.required, forms_1.Validators.pattern("[0-9]+")])],
            'laufzeit': [null, forms_1.Validators.compose([forms_1.Validators.required, forms_1.Validators.pattern("[0-9]+")])],
        });
    }
    submitForm(value) {
        this._financeService.requestKredit({ betrag: value.betrag, laufzeit: value.laufzeit });
        console.log("new Credit");
    }
};
NewKreditComponent = __decorate([
    core_1.Component({
        selector: 'new-kredit-component',
        templateUrl: '../../../../templates/components/home.components/finance.component/new-kredit.component.html',
    }),
    __metadata("design:paramtypes", [sales_service_1.SalesService, forms_1.FormBuilder])
], NewKreditComponent);
exports.NewKreditComponent = NewKreditComponent;
//# sourceMappingURL=new-kredit.component.js.map