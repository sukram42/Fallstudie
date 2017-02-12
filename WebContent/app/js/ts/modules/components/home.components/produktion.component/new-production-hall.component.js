/**
 * Created by boebel on 20.01.2017.
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
const produktion_service_1 = require("../../../services/produktion.service");
let NewProductionHallComponent = class NewProductionHallComponent {
    constructor(proService) {
        this.proService = proService;
        this.frei = 0;
        this.gesamt = 0;
        this.getHallCapacity();
        proService.getMachinesSubject().asObservable().subscribe(data => this.getHallCapacity());
    }
    kaufeHalle(size) {
        this.proService.kaufeProduktionshalle(size).subscribe(data => data, err => console.log(err), () => this.getHallCapacity());
    }
    getHallCapacity() {
        this.proService.getProduktionshallenkapazitaet().subscribe(data => {
            this.frei = data.free;
            this.gesamt = data.gesamt;
        });
    }
};
NewProductionHallComponent = __decorate([
    core_1.Component({
        selector: 'new-production-hall-component',
        templateUrl: '../../../../templates/components/home.components/produktion.component/new-production-hall.component.html',
    }),
    __metadata("design:paramtypes", [produktion_service_1.ProduktionService])
], NewProductionHallComponent);
exports.NewProductionHallComponent = NewProductionHallComponent;
//# sourceMappingURL=new-production-hall.component.js.map