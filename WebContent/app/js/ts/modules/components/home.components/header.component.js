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
/**
 * Created by boebel on 04.01.2017.
 */
const core_1 = require("@angular/core");
const home_service_1 = require("../../services/home.service");
const keyfigures_service_1 = require("../../services/keyfigures.service");
const hr_service_1 = require("../../services/hr.service");
const produktion_service_1 = require("../../services/produktion.service");
let HeaderComponent = class HeaderComponent {
    constructor(_homeService, _keyFigures, _hrService, _proService) {
        this._homeService = _homeService;
        this._keyFigures = _keyFigures;
        this._hrService = _hrService;
        this._proService = _proService;
        this.bankrupt = false;
        this.init();
        this._homeService.getCompany()
            .subscribe(data => this.companyName = data.name, err => alert(err));
        _homeService.isBankrupt().subscribe(data => {
            if (data == "true")
                this.bankrupt = true;
        });
        this._proService.getWarehouseSubject()
            .asObservable().subscribe(data => this.getCapacity(), err => console.log(err));
        this._hrService.getEmployeeSubject().subscribe(data => { this.getEmployeeCount(); }, err => { });
    }
    init() {
        this.getCapacity();
        this.getEmployeeCount();
        this._homeService.getTime().subscribe(data => this.time = data);
        this.getLiquideMittel();
    }
    getEmployeeCount() {
        this._keyFigures.getEmployeeCount().subscribe(data => this.mitarbeiterCount = data);
    }
    getCapacity() {
        this._proService.getLagerkapazitaet().subscribe(data => {
            this.freieLagerPlaetze = data.free;
            this.gesamteLagerPlaetze = data.gesamt;
        });
        this._proService.getLagerkapazitaetIntervall().subscribe(data => {
            this.freieLagerPlaetze = data.free;
            this.gesamteLagerPlaetze = data.gesamt;
        });
    }
    getLiquideMittel() {
        this._keyFigures.getLiquideMittel().subscribe(data => this.liquideMittel = data.liquideMittel, err => console.log(err));
    }
    logOutRequest() {
        this._homeService.logOut().subscribe(data => data, err => console.log(err), () => this.logOut());
    }
    logOut() {
        window.localStorage.removeItem("auth_key");
        window.location.href = "index.html";
    }
    runden(x) {
        return Math.round(x * 100) / 100;
    }
};
HeaderComponent = __decorate([
    core_1.Component({
        selector: 'header-component',
        templateUrl: '../../../../templates/components/home.components/header.component.html'
    }),
    __metadata("design:paramtypes", [home_service_1.HomeService, keyfigures_service_1.KeyFiguresService, hr_service_1.HRService, produktion_service_1.ProduktionService])
], HeaderComponent);
exports.HeaderComponent = HeaderComponent;
//# sourceMappingURL=header.component.js.map