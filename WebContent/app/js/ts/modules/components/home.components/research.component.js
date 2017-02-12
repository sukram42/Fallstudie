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
const core_1 = require("@angular/core");
const hr_service_1 = require("../../services/hr.service");
const research_service_1 = require("../../services/research.service");
let ResearchComponent = class ResearchComponent {
    constructor(_hrService, _researchService) {
        this._hrService = _hrService;
        this._researchService = _researchService;
        this.error = false;
        _hrService.getEmployeesResearch().subscribe(data => {
            this.employees = data, console.log(data);
        });
        _researchService.getResearchSubject().subscribe(data => {
            if (data.startsWith("ERROR:M"))
                this.error = true;
            else
                this.error = false;
        });
    }
};
ResearchComponent = __decorate([
    core_1.Component({
        selector: 'home-component',
        templateUrl: '../../../../templates/components/home.components/research.component.html',
    }),
    __metadata("design:paramtypes", [hr_service_1.HRService, research_service_1.ResearchService])
], ResearchComponent);
exports.ResearchComponent = ResearchComponent;
//# sourceMappingURL=research.component.js.map