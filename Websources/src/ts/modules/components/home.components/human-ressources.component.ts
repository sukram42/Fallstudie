/**
 * Created by boebel on 09.01.2017.
 */


import { Component } from '@angular/core';
import {HRService} from "../../services/hr.service";

@Component({
    selector   : 'human-ressources-component',
    templateUrl: '../../../../templates/components/home.components/human-ressources.component.html',
})

export class HRComponent {
    employees;
    error;
    constructor(hrService : HRService)
    {
        hrService.getEmployeesHR().subscribe(
            data=>{this.employees = data,console.log(data)}
        );

        hrService.getEmployeeSubject()
            .subscribe((data) => {
                if (data.toString().startsWith("ERROR"))
                    this.error = data;
                else
                {
                    this.error = undefined;}
            });
    }
}