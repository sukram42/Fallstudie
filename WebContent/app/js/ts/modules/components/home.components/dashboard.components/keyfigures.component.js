/**
 * Created by boebel on 10.01.2017.
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
const keyfigures_service_1 = require("../../../services/keyfigures.service");
const hr_service_1 = require("../../../services/hr.service");
let KeyfiguresComponent = class KeyfiguresComponent {
    constructor(hrService, keyFigures) {
        this.hrService = hrService;
        this.keyFigures = keyFigures;
        this.progress = 50;
    }
    ngOnInit() {
        this.newTitle = this.title.toLowerCase()
            .replace("ä", "ae")
            .replace("ü", "ue")
            .replace("ö", "oe")
            .replace("ß", "ss");
        this.init();
    }
    init() {
        this.keyFigures.getSoftKeyFigure(this.newTitle)
            .subscribe(data => (this.progress = Math.round((data * 100) * 100) / 100), err => console.log(err));
    }
};
__decorate([
    core_1.Input('title'),
    __metadata("design:type", String)
], KeyfiguresComponent.prototype, "title", void 0);
__decorate([
    core_1.Input('progress'),
    __metadata("design:type", Object)
], KeyfiguresComponent.prototype, "newTitle", void 0);
KeyfiguresComponent = __decorate([
    core_1.Component({
        selector: 'kennzahl',
        templateUrl: '../../../../templates/components/home.components/dashboard.components/keyfigures.component.html',
        providers: [hr_service_1.HRService, keyfigures_service_1.KeyFiguresService]
    }),
    __metadata("design:paramtypes", [hr_service_1.HRService, keyfigures_service_1.KeyFiguresService])
], KeyfiguresComponent);
exports.KeyfiguresComponent = KeyfiguresComponent;
//# sourceMappingURL=keyfigures.component.js.map