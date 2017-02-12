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
const produktion_service_1 = require("../../services/produktion.service");
const hr_service_1 = require("../../services/hr.service");
let ProduktionComponent = class ProduktionComponent {
    constructor(proService, hrService) {
        this.proService = proService;
        this.hrService = hrService;
        hrService.getEmployeesProduktion().subscribe(data => { this.employees = data, console.log(data); });
        this.proService.getMachinesSubject().asObservable()
            .subscribe(data => {
            if (data.toString().startsWith("ERROR"))
                this.errorMaschinen = data;
            else if (data.toString().startsWith("SOLD"))
                this.sold = data.toString().substring(5).trim();
            else {
                this.errorMaschinen = undefined;
                this.sold = undefined;
            }
        });
        this.proService.getProductlinesSubject()
            .subscribe(data => {
            if (data.toString().startsWith("ERROR"))
                this.errorLinie = data;
            else
                this.errorLinie = undefined;
        });
    }
};
ProduktionComponent = __decorate([
    core_1.Component({
        selector: 'home-component',
        templateUrl: '../../../../templates/components/home.components/produktion.component.html',
    }),
    __metadata("design:paramtypes", [produktion_service_1.ProduktionService, hr_service_1.HRService])
], ProduktionComponent);
exports.ProduktionComponent = ProduktionComponent;
//# sourceMappingURL=produktion.component.js.map