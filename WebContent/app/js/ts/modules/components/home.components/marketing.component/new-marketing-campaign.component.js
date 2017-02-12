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
const forms_1 = require("@angular/forms");
const marketing_service_1 = require("../../../services/marketing.service");
let NewMarketingCampaignComponent = class NewMarketingCampaignComponent {
    constructor(marketing, fb) {
        this.marketing = marketing;
        this.fb = fb;
        this.newCampaign = fb.group({
            'art': [null, forms_1.Validators.required],
            'laufzeit': [null, forms_1.Validators.compose([forms_1.Validators.required, forms_1.Validators.pattern("[0-9]+")])],
        });
    }
    submitForm(value) {
        console.log("STARTE DAS ERSTELLEN VON MARKETING MASSNAHMEN");
        this.marketing.startCampaign(value.art, value.laufzeit);
    }
};
NewMarketingCampaignComponent = __decorate([
    core_1.Component({
        selector: 'new-marketing-campaign',
        templateUrl: '../../../../templates/components/home.components/marketing.component/new-marketing-campaign.component.html',
    }),
    __metadata("design:paramtypes", [marketing_service_1.MarketingService, forms_1.FormBuilder])
], NewMarketingCampaignComponent);
exports.NewMarketingCampaignComponent = NewMarketingCampaignComponent;
//# sourceMappingURL=new-marketing-campaign.component.js.map