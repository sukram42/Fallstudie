/**
 * Created by boebel on 24.01.2017.
 */

import {Component} from "@angular/core";
import {SalesService} from "../../../services/sales.service";

@Component({
    selector: 'opportunities-list-component',
    templateUrl: '../../../../templates/components/home.components/sales.component/opportunities-list.component.html',
})

export class OpportunitiesListComponent {
    opportunities;

    constructor(private salesService: SalesService) {
        this.getOpportunities()
        this.salesService.getOpportunitiesSubject().subscribe(data=>this.getOpportunities(),err=>console.log(err));
    }


    getOpportunities()
    {
        this.salesService.getOpportunities().subscribe(data=>{this.opportunities = data,console.log(data)},err=>console.log(err));
    }

}