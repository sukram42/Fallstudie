/**
 * Created by boebel on 24.01.2017.
 */

import {Component} from "@angular/core";
import {SalesService} from "../../../services/sales.service";

@Component({
    selector: 'ausschreibungen-list-component',
    templateUrl: '../../../../templates/components/home.components/sales.component/ausschreibungen-list.component.html',
})

export class AusschreibungListComponent {
    ausschreibungen;

    constructor(private salesService: SalesService) {
        this.salesService.getAusschreibungen().subscribe(data=>this.ausschreibungen = data,err=>console.log(err));
    }

    bewerben(index)
    {
        this.salesService.bewerben(index);
    }

}