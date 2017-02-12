/**
 * Created by boebel on 20.01.2017.
 */


import {ProduktionService} from "../../../services/produktion.service";
import {Component} from "@angular/core";

@Component({
    selector: 'productline-list-component',
    templateUrl: '../../../../templates/components/home.components/produktion.component/productline-list.component.html',
})

export class ProductLineComponent {
    data;

    constructor(private proService: ProduktionService) {
        this.loadLists();
        this.proService.getProductlinesSubject().subscribe(data=>this.loadLists());
    }

    loadLists()
    {
        this.proService.getProduktlinien().subscribe(data=>{
            this.data = data;
            console.log(data);
        });
    }

    runden(x)
    {
        return Math.round(x * 100) / 100 ;
    }


}