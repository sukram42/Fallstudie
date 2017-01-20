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
    }

    kaufeLager(size) {
        this.proService.kaufeLager(size).subscribe(data => console.log(data), err => console.log(err),()=>this.getCapacity());
    }
    getCapacity()
    {
        this.proService.getLagerkapazitaet().subscribe(data=>{
            this.frei = data.free;
            this.gesamt = data.gesamt;
        },);
    }
}