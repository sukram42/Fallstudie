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
const http_1 = require("@angular/http");
require("rxjs/Rx");
const Subject_1 = require("rxjs/Subject");
let MarketingService = class MarketingService {
    constructor(http) {
        this.http = http;
        this.campaignSubject = new Subject_1.Subject();
    }
    startCampaign(art, laufzeit) {
        return this.http.post('http://localhost:8080/rest/companies/marketing/campaigns/' + art, laufzeit)
            .map(res => res.text())
            .subscribe(data => {
            console.log("DATA: " + data);
            if (data.toString().startsWith("ERROR"))
                this.campaignSubject.next(data);
            else
                this.campaignSubject.next("Kampagne gestartet");
        });
    }
    getCosts() {
        return this.http.get('http://localhost:8080/rest/companies/marketing/costs').map(res => res.json());
    }
    getCampaignSubject() {
        return this.campaignSubject.asObservable();
    }
    getCampaigns() {
        return this.http.get('http://localhost:8080/rest/companies/marketing/campaigns')
            .map(res => res.json());
    }
    stopCampaign(art) {
        return this.http.delete('http://localhost:8080/rest/companies/marketing/campaigns/' + art).map(res => res.text());
    }
    isCampaignAktive(art) {
        return this.http.get('http://localhost:8080/rest/companies/marketing/campaigns/' + art).map(res => res.text());
    }
};
MarketingService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], MarketingService);
exports.MarketingService = MarketingService;
//# sourceMappingURL=marketing.service.js.map