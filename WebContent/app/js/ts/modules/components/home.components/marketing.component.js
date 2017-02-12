/**
 * Created by boebel on 09.01.2017.
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
/**
 * Created by boebel on 04.01.2017.
 */
const core_1 = require("@angular/core");
const hr_service_1 = require("../../services/hr.service");
const marketing_service_1 = require("../../services/marketing.service");
let MarketingComponent = class MarketingComponent {
    constructor(_hrService, _marketingService) {
        this._hrService = _hrService;
        this._marketingService = _marketingService;
        _hrService.getEmployeesMarketing().subscribe(data => this.employees = data);
        _marketingService.getCampaignSubject().subscribe(data => {
            if (data.toString().startsWith("ERROR"))
                this.error = true;
            else
                this.error = undefined;
        });
        _marketingService.getCosts().subscribe(data => {
            this.costs = data;
        });
    }
};
MarketingComponent = __decorate([
    core_1.Component({
        selector: 'home-component',
        templateUrl: '../../../../templates/components/home.components/marketing.component.html',
    }),
    __metadata("design:paramtypes", [hr_service_1.HRService, marketing_service_1.MarketingService])
], MarketingComponent);
exports.MarketingComponent = MarketingComponent;
//# sourceMappingURL=marketing.component.js.map