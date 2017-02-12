/**
 * Created by boebel on 17.01.2017.
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
const hr_service_1 = require("../../../services/hr.service");
let SozialeLeistungenComponent = class SozialeLeistungenComponent {
    constructor(hrService) {
        this.hrService = hrService;
        this.init();
        this.hrService.getSocialProjectSubject().subscribe(data => this.init());
    }
    init() {
        this.hrService.getSocialProject().subscribe(data => { this.socialProjects = data, console.log(data); });
    }
    changeProjectProject(name) {
        this.hrService.changeProjectActivity(name);
    }
};
SozialeLeistungenComponent = __decorate([
    core_1.Component({
        selector: 'soziale-leistungen',
        templateUrl: '../../../../templates/components/home.components/human-ressources.component/social-project.component.html',
    }),
    __metadata("design:paramtypes", [hr_service_1.HRService])
], SozialeLeistungenComponent);
exports.SozialeLeistungenComponent = SozialeLeistungenComponent;
//# sourceMappingURL=soziale-leistungen.component.js.map