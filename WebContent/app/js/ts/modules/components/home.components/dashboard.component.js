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
const keyfigures_service_1 = require("../../services/keyfigures.service");
const home_service_1 = require("../../services/home.service");
let DashboardComponent = class DashboardComponent {
    constructor(_keyFigures, _homeService) {
        this._keyFigures = _keyFigures;
        this._homeService = _homeService;
        this.type = 'radar';
        this.options = {
            responsive: true,
            maintainAspectRatio: false,
            defaultFontSize: 100,
        };
        this.type2 = 'bar';
        this.aufwandchar = {
            labels: [],
            datasets: [
                {
                    label: "Erträge",
                    backgroundColor: "rgba(255,173,41,0.7)",
                    borderColor: "rgba(255,173,41,1)",
                    data: []
                },
                {
                    label: "Aufwendungen",
                    backgroundColor: "rgba(49,53,61,0.7)",
                    borderColor: "rgba(49,53,61,1)",
                    data: []
                }
            ]
        };
        this.options2 = {
            responsive: true,
            maintainAspectRatio: false,
            defaultFontSize: 100,
        };
        this.type3 = 'polarArea';
        this.marktanteileData = {
            labels: [],
            datasets: [
                {
                    label: "Erträge",
                    backgroundColor: ["rgba(255,173,41,0.7)", "rgba(49,35,61,0.7)", "rgba(28,29,33,0.7)"],
                    borderColor: ["rgba(255,173,41,1)", "rgba(49,35,61,1)", "rgba(28,29,33,1)"],
                    data: []
                }
            ]
        };
        this.options3 = {
            responsive: true,
            maintainAspectRatio: false,
            defaultFontSize: 100,
        };
        _keyFigures.getArchivKennzahlen()
            .subscribe(data => this.fillAufwandChar(data));
        _keyFigures.getSoftKeyFigures()
            .subscribe(content => {
            if (!this.data) {
                this.data = {
                    labels: ["Mitarbeiterzufriedenheit", "Kundenzufriedenheit", "Bekanntheitsgrad", "Image", "Marktanteil"],
                    datasets: [
                        {
                            label: "Kennzahlen",
                            backgroundColor: "rgba(255,173,41,0.7)",
                            borderColor: "rgba(255,173,41,1)",
                            data: [content.mitarbeiterzufriedenheit, content.kundenzufriedenheit, content.bekanntheitsgrad, content.image, 0]
                        } //,
                    ]
                };
            }
            else {
                this.data.datasets[0].data[0] = content.mitarbeiterzufriedenheit;
                this.data.datasets[0].data[1] = content.kundenzufriedenheit;
                this.data.datasets[0].data[2] = content.bekanntheitsgrad;
                this.data.datasets[0].data[3] = content.image;
                this.data.datasets[0].data[4] = content.marktanteil;
            }
        });
    }
    ngOnInit() {
    }
    fillAufwandChar(data) {
        for (var key in data) {
            if (data.hasOwnProperty(key)) {
                var guv = data[key];
                if (!this.aufwandchar.labels.includes(guv.key)) {
                    this.aufwandchar.labels.push(guv.key);
                    this.aufwandchar.datasets[0].data.push(guv.value.erloeseArchiv);
                    this.aufwandchar.datasets[1].data.push(guv.value.aufwendungenArchiv);
                }
            }
        }
        this.chart.chart.update();
    }
    fillMarktanteile() {
        this._homeService.getMarktanteile().subscribe(data => {
            for (var key in data) {
                if (data.hasOwnProperty(key)) {
                    if (!this.aufwandchar.labels.includes(key)) {
                        this.marktanteileData.labels.push(key);
                        this.marktanteileData.datasets[0].data.push(data[key]);
                    }
                }
            }
        });
        this.marktanteile.chart.update();
    }
};
__decorate([
    core_1.ViewChild('barchart'),
    __metadata("design:type", Object)
], DashboardComponent.prototype, "chart", void 0);
__decorate([
    core_1.ViewChild('marktchart'),
    __metadata("design:type", Object)
], DashboardComponent.prototype, "marktanteile", void 0);
DashboardComponent = __decorate([
    core_1.Component({
        selector: 'home-component',
        templateUrl: '../../../../templates/components/home.components/dashboard.component.html',
    }),
    __metadata("design:paramtypes", [keyfigures_service_1.KeyFiguresService, home_service_1.HomeService])
], DashboardComponent);
exports.DashboardComponent = DashboardComponent;
//# sourceMappingURL=dashboard.component.js.map