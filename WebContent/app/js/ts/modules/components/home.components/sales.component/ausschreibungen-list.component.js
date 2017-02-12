/**
 * Created by boebel on 24.01.2017.
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
const sales_service_1 = require("../../../services/sales.service");
let AusschreibungListComponent = class AusschreibungListComponent {
    constructor(salesService) {
        this.salesService = salesService;
        this.salesService.getAusschreibungen().subscribe(data => this.ausschreibungen = data, err => console.log(err));
    }
    bewerben(index) {
        this.salesService.bewerben(index);
    }
};
AusschreibungListComponent = __decorate([
    core_1.Component({
        selector: 'ausschreibungen-list-component',
        templateUrl: '../../../../templates/components/home.components/sales.component/ausschreibungen-list.component.html',
    }),
    __metadata("design:paramtypes", [sales_service_1.SalesService])
], AusschreibungListComponent);
exports.AusschreibungListComponent = AusschreibungListComponent;
//# sourceMappingURL=ausschreibungen-list.component.js.map