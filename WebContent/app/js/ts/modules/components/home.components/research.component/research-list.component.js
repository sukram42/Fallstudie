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
const research_service_1 = require("../../../services/research.service");
const home_service_1 = require("../../../services/home.service");
let ResearchListComponent = class ResearchListComponent {
    constructor(_homeService, _researchService) {
        this._homeService = _homeService;
        this._researchService = _researchService;
        this.loadList();
        this._researchService.getResearchSubject().subscribe(() => this.loadList());
        this._homeService.getTime().subscribe(() => this.loadList());
    }
    loadList() {
        this._researchService.getProjects().subscribe(data => this.active = data);
    }
};
ResearchListComponent = __decorate([
    core_1.Component({
        selector: 'research-list',
        templateUrl: '../../../../templates/components/home.components/research.component/research-list.component.html',
    }),
    __metadata("design:paramtypes", [home_service_1.HomeService, research_service_1.ResearchService])
], ResearchListComponent);
exports.ResearchListComponent = ResearchListComponent;
//# sourceMappingURL=research-list.component.js.map