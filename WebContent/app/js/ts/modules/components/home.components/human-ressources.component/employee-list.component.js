/**
 * Created by boebel on 12.01.2017.
 */
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
const hr_service_1 = require("../../../services/hr.service");
let EmployeeListComponent = class EmployeeListComponent {
    constructor(hrService) {
        this.hrService = hrService;
        this.mitarbeiterList = [];
        hrService.getEmployeeSubject().subscribe((data) => this.setValues(), err => { });
        this.setValues();
    }
    setValues() {
        this.hrService.getEmployees().subscribe(data => this.mitarbeiterList = data, err => { });
    }
    ngOnInit() {
    }
    fire(opfer) {
        this.hrService.fire(opfer);
    }
};
EmployeeListComponent = __decorate([
    core_1.Component({
        selector: 'employee-list',
        templateUrl: '../../../../templates/components/home.components/human-ressources.component/employee-list.component.html'
    }),
    __metadata("design:paramtypes", [hr_service_1.HRService])
], EmployeeListComponent);
exports.EmployeeListComponent = EmployeeListComponent;
//# sourceMappingURL=employee-list.component.js.map