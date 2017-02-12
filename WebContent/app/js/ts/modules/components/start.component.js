/**
 * Created by boebel on 04.01.2017.
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
const home_service_1 = require("../services/home.service");
let StartComponent = class StartComponent {
    constructor(homeService) {
        this.homeService = homeService;
        var validateToken = this.homeService.validateToken();
        if (validateToken) {
            validateToken.subscribe(data => {
                if (data.trim() == "true") {
                    window.location.href = "home.html";
                }
            }), err => { console.log("Error"); };
        }
    }
};
StartComponent = __decorate([
    core_1.Component({
        selector: 'start-component',
        templateUrl: '../../../../templates/components/start.component.html',
        providers: [home_service_1.HomeService]
    }),
    __metadata("design:paramtypes", [home_service_1.HomeService])
], StartComponent);
exports.StartComponent = StartComponent;
//# sourceMappingURL=start.component.js.map