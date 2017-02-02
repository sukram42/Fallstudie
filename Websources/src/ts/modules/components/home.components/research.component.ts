/**
 * Created by boebel on 09.01.2017.
 */


import { Component } from '@angular/core';
import {HRService} from "../../services/hr.service";

@Component({
    selector   : 'home-component',
    templateUrl: '../../../../templates/components/home.components/research.component.html',

})

export class ResearchComponent {

    employees;

    constructor(private hrService:HRService) {
        hrService.getEmployeesResearch().subscribe(
            data => {
                this.employees = data, console.log(data)
            }
        );
    }
}