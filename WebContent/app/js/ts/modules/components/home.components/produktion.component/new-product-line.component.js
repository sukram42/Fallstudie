/**
 * Created by boebel on 18.01.2017.
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
const produktion_service_1 = require("../../../services/produktion.service");
let NewProductLineComponent = class NewProductLineComponent {
    constructor(proService, fb) {
        this.proService = proService;
        this.fb = fb;
        this.newProduktLinie = fb.group({
            'produkt': [null, forms_1.Validators.required],
            'quality': [null, forms_1.Validators.required],
            'menge': [null, forms_1.Validators.compose([forms_1.Validators.required, forms_1.Validators.pattern("[0-9]+")])],
            'laufzeit': [null, forms_1.Validators.compose([forms_1.Validators.required, forms_1.Validators.pattern("[0-9]+")])],
        });
    }
    submitForm(data) {
        this.proService.produzieren(data);
    }
};
NewProductLineComponent = __decorate([
    core_1.Component({
        selector: 'new-product-line-modal',
        templateUrl: '../../../../templates/components/home.components/produktion.component/new-product-line.component.html',
    }),
    __metadata("design:paramtypes", [produktion_service_1.ProduktionService, forms_1.FormBuilder])
], NewProductLineComponent);
exports.NewProductLineComponent = NewProductLineComponent;
//# sourceMappingURL=new-product-line.component.js.map