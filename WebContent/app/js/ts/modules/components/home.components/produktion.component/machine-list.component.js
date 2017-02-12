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
const produktion_service_1 = require("../../../services/produktion.service");
const core_1 = require("@angular/core");
const home_service_1 = require("../../../services/home.service");
let MachineListComponent = class MachineListComponent {
    constructor(_proService, homeService) {
        this._proService = _proService;
        this.homeService = homeService;
        this.loadList();
        _proService.getMachinesSubject().asObservable().subscribe(data => this.loadList());
        this.homeService.getTime().subscribe(() => this.refreshStatus());
    }
    loadList() {
        this._proService.getMachines()
            .subscribe(data => { this.data = data, console.log(data); });
    }
    refreshStatus() {
        this._proService.getMachines()
            .subscribe(data => {
            for (var i = 0; i < data.length; i++) {
                this.data[i].status = data[i].status;
            }
        });
    }
    repairMachine(index) {
        this._proService.repairMachines(index).subscribe(() => { }, err => console.log(err), () => this.loadList());
    }
    sellMachine(index) {
        this._proService.sell(index);
    }
    roundStatus(machine) {
        return Math.round(machine.status * 100);
    }
};
MachineListComponent = __decorate([
    core_1.Component({
        selector: 'machine-list-component',
        templateUrl: '../../../../templates/components/home.components/produktion.component/machine-list.component.html',
    }),
    __metadata("design:paramtypes", [produktion_service_1.ProduktionService, home_service_1.HomeService])
], MachineListComponent);
exports.MachineListComponent = MachineListComponent;
//# sourceMappingURL=machine-list.component.js.map