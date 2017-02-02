/**
 * Created by boebel on 09.01.2017.
 */

/**
 * Created by boebel on 04.01.2017.
 */

import { Component } from '@angular/core';
import {HRService} from "../../services/hr.service";

@Component({
    selector   : 'home-component',
    templateUrl: '../../../../templates/components/home.components/marketing.component.html',

})

export class MarketingComponent {
    employees;
    constructor(private hrService:HRService)
    {
        hrService.getEmployeesMarketing().subscribe(
            data=>{this.employees = data,console.log(data)}
        );

    }
}