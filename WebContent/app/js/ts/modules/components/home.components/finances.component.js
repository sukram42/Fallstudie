/**
 * Created by boebel on 26.01.2017.
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
const sales_service_1 = require("../../services/sales.service");
let FinancesComponent = class FinancesComponent {
    constructor(_salesService) {
        this._salesService = _salesService;
        this.errorL = false;
        this.errorV = false;
        this.errorM = false;
        this.errorB = false;
        _salesService.getCreditSubject().subscribe(data => {
            if (data.startsWith("ERROR:L"))
                this.errorL = true;
            else if (data.startsWith("ERROR:V"))
                this.errorV = true;
            else if (data.startsWith("ERROR:M"))
                this.errorM = true;
            else if (data.startsWith("ERROR:B"))
                this.errorB = true;
            else {
                this.errorL = false;
                this.errorV = false;
                this.errorM = false;
                this.errorB = false;
            }
        });
    }
};
FinancesComponent = __decorate([
    core_1.Component({
        selector: 'finances-component',
        templateUrl: '../../../../templates/components/home.components/finances.component.html',
    }),
    __metadata("design:paramtypes", [sales_service_1.SalesService])
], FinancesComponent);
exports.FinancesComponent = FinancesComponent;
//# sourceMappingURL=finances.component.js.map