/**
 * Created by boebel on 09.01.2017.
 */

/**
 * Created by boebel on 04.01.2017.
 */

import { Component } from '@angular/core';
import {HRService} from "../../services/hr.service";
import {MarketingService} from "../../services/marketing.service";

@Component({
    selector   : 'home-component',
    templateUrl: '../../../../templates/components/home.components/marketing.component.html',

})

export class MarketingComponent {
    employees;
    error;
    costs;
    constructor(private _hrService:HRService, private _marketingService:MarketingService)
    {
        _hrService.getEmployeesMarketing().subscribe(
            data=>this.employees = data
        );

        _marketingService.getCampaignSubject().subscribe(data=> {
            if (data.toString().startsWith("ERROR"))
                this.error = true;
            else
                this.error = undefined;
        });

        _marketingService.getCosts().subscribe(data=>{
            this.costs=data;
        });

    }
}