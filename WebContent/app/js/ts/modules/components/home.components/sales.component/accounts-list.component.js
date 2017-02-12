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
const home_service_1 = require("../../../services/home.service");
let AccountsListComponent = class AccountsListComponent {
    constructor(salesService, _homeService) {
        this.salesService = salesService;
        this._homeService = _homeService;
        this.loadList();
        this._homeService.getTime().subscribe(data => this.loadList());
    }
    loadList() {
        this.salesService.getAccounts().subscribe(data => { this.accounts = data; }, err => console.log(err));
    }
};
AccountsListComponent = __decorate([
    core_1.Component({
        selector: 'accounts-list-component',
        templateUrl: '../../../../templates/components/home.components/sales.component/accounts-list.component.html',
    }),
    __metadata("design:paramtypes", [sales_service_1.SalesService, home_service_1.HomeService])
], AccountsListComponent);
exports.AccountsListComponent = AccountsListComponent;
//# sourceMappingURL=accounts-list.component.js.map