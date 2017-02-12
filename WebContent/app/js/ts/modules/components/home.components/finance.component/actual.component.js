/**
 * Created by boebel on 04.02.2017.
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
const keyfigures_service_1 = require("../../../services/keyfigures.service");
const home_service_1 = require("../../../services/home.service");
let ActualComponent = class ActualComponent {
    constructor(_homeService, _keyfigService) {
        this._homeService = _homeService;
        this._keyfigService = _keyfigService;
    }
    ngOnInit() {
        this.loadKonten();
        this._homeService.getTime().subscribe((data) => {
            this.loadKonten();
            this.time = data;
        });
    }
    loadKonten() {
        this._keyfigService.getKeyFigures().subscribe(data => {
            this.keyfigures = data;
        });
    }
    runden(x) {
        return Math.round(x * 100) / 100;
    }
};
ActualComponent = __decorate([
    core_1.Component({
        selector: 'actual-component',
        templateUrl: '../../../../templates/components/home.components/finance.component/actual.component.html',
    }),
    __metadata("design:paramtypes", [home_service_1.HomeService, keyfigures_service_1.KeyFiguresService])
], ActualComponent);
exports.ActualComponent = ActualComponent;
//# sourceMappingURL=actual.component.js.map