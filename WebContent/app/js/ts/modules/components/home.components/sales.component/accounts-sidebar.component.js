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
 * Created by boebel on 02.02.2017.
 */
const core_1 = require("@angular/core");
const sales_service_1 = require("../../../services/sales.service");
let AccountsSidebar = class AccountsSidebar {
    constructor(_salesService) {
        this._salesService = _salesService;
        this._salesService.getAccounts().subscribe(data => {
            this.accounts = data;
        });
        this._salesService.getAusbringung().subscribe(data => {
            this.ausbringung = data;
        });
        this._salesService.getBestand().subscribe(data => {
            this.bestand = data;
        });
    }
};
AccountsSidebar = __decorate([
    core_1.Component({
        selector: 'accounts-sidebar-component',
        templateUrl: '../../../../templates/components/home.components/sales.component/accounts-sidebar.component.html',
    }),
    __metadata("design:paramtypes", [sales_service_1.SalesService])
], AccountsSidebar);
exports.AccountsSidebar = AccountsSidebar;
//# sourceMappingURL=accounts-sidebar.component.js.map