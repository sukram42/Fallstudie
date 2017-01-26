/**
 * Created by boebel on 09.01.2017.
 */

/**
 * Created by boebel on 04.01.2017.
 */

import { Component } from '@angular/core';
import {SalesService} from "../../services/sales.service";

@Component({
    selector   : 'home-component',
    templateUrl: '../../../../templates/components/home.components/sales.component.html',

})

export class SalesComponent {
    errorOpportunities;
    constructor(private _salesService:SalesService)
    {
        _salesService.getOpportunitiesSubject().subscribe(data=>data,err=>this.errorOpportunities=err)
    }
}