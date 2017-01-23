/**
 * Created by boebel on 20.01.2017.
 */


import {Component} from '@angular/core';
import {FormControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProduktionService} from "../../../services/produktion.service";

@Component({
    selector: 'new-warehouse-component',
    templateUrl: '../../../../templates/components/home.components/produktion.component/new-warehouse.component.html',
})

export class NewWarehouseComponent {

    frei = 0;
    gesamt = 0;


    constructor(private proService: ProduktionService) {
        this.getCapacity();
        this.proService.getWarehouseSubject()
            .asObservable().subscribe(data=>this.getCapacity(),err=>console.log(err));
}

    kaufeLager(size) {
        this.proService.kaufeLager(size).subscribe(data =>this.proService.getWarehouseSubject().next("new Lager"), err =>this.proService.getWarehouseSubject().error(err));
    }
    getCapacity()
    {
        this.proService.getLagerkapazitaet().subscribe(data=>{
            this.frei = data.free;
            this.gesamt = data.gesamt;
        });
    }
}