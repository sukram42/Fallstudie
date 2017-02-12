/**
 * Created by boebel on 09.01.2017.
 */

/**
 * Created by boebel on 04.01.2017.
 */

import { Component } from '@angular/core';
import {SalesService} from "../../services/sales.service";
import {HRService} from "../../services/hr.service";

@Component({
    selector   : 'home-component',
    templateUrl: '../../../../templates/components/home.components/sales.component.html',

})

export class SalesComponent {
    errorOpportunities;
    employees;
    errorBereitsBeworben;
    constructor(private _hrService:HRService,private _salesService:SalesService)
    {
        _salesService.getOpportunitiesSubject().subscribe(data=>
        {
            if(data.toString().startsWith("ERROR:M"))this.errorOpportunities=true;
            else if(data.toString().startsWith("ERROR:BB"))this.errorBereitsBeworben=true;
        });
        _hrService.getEmployeesSales().subscribe(
            data=>{this.employees = data,console.log(data)}
        );
    }
}