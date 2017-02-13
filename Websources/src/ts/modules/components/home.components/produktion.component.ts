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
    employees;
    errorMaschinen;
    errorLinie;
    sold;

    constructor(private proService:ProduktionService ,private hrService:HRService)
    {
        hrService.getEmployeesProduktion().subscribe(
            data=>{this.employees = data,console.log(data)}
            );

        this.proService.getMachinesSubject().asObservable()
            .subscribe(data=>{
                if(data.toString().startsWith("ERROR"))
                    this.errorMaschinen = data;
                else if (data.toString().startsWith("SOLD"))
                    this.sold = data.toString().substring(5).trim();
                else
                {
                    this.errorMaschinen = undefined;
                    this.sold = undefined;
                }
            });

        this.proService.getProductlinesSubject()
            .subscribe(data=>{
                if(data.toString().startsWith("ERROR"))
                    this.errorLinie = data;
                else this.errorLinie = undefined;
            });

    }


}