/**
 * Created by boebel on 02.02.2017.
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
const marketing_service_1 = require("../../../services/marketing.service");
const home_service_1 = require("../../../services/home.service");
let CampagneListComponent = class CampagneListComponent {
    constructor(_homeService, _markService) {
        this._homeService = _homeService;
        this._markService = _markService;
        this.loadList();
        _markService.getCampaignSubject().subscribe(() => this.loadList());
        _homeService.getTime().subscribe(() => this.loadList());
    }
    loadList() {
        this._markService.getCampaigns().subscribe(data => { this.campaigns = data; });
    }
};
CampagneListComponent = __decorate([
    core_1.Component({
        selector: 'campaign-list-component',
        templateUrl: '../../../../templates/components/home.components/marketing.component/campaign-list.component.html',
    }),
    __metadata("design:paramtypes", [home_service_1.HomeService, marketing_service_1.MarketingService])
], CampagneListComponent);
exports.CampagneListComponent = CampagneListComponent;
//# sourceMappingURL=campaign-list.component.js.map