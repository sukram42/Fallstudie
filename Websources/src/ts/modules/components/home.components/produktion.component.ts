/**
 * Created by boebel on 09.01.2017.
 */

/**
 * Created by boebel on 04.01.2017.
 */

import { Component } from '@angular/core';
import {ProduktionService} from "../../services/produktion.service";
import {HRService} from "../../services/hr.service";

@Component({
    selector   : 'home-component',
    templateUrl: '../../../../templates/components/home.components/produktion.component.html',

})

export class ProduktionComponent {
    employees
    errorMaschinen;
    errorLinie;
    constructor(private proService:ProduktionService ,private hrService:HRService)
    {
        hrService.getEmployeesProduktion().subscribe(
            data=>{this.employees = data,console.log(data)}
            );
        this.proService.getMachinesSubject().asObservable().subscribe(data=>data,err=>this.errorMaschinen = err);
        this.proService.getProductlinesSubject().subscribe(data=>data,err=>this.errorLinie = err);

    }


}