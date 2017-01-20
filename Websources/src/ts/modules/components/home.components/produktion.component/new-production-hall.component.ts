/**
 * Created by boebel on 20.01.2017.
 */


import {Component} from '@angular/core';
import {FormControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProduktionService} from "../../../services/produktion.service";

@Component({
    selector: 'new-production-hall-component',
    templateUrl: '../../../../templates/components/home.components/produktion.component/new-production-hall.component.html',
})

export class NewProductionHallComponent {

    frei = 0;
    gesamt = 0;


    constructor(private proService: ProduktionService) {
        this.getHallCapacity();
        proService.getMachinesSubject().asObservable().subscribe(data=>this.getHallCapacity());
    }

    kaufeHalle(size) {
        this.proService.kaufeProduktionshalle(size).subscribe(data => console.log(data), err => console.log(err),()=>this.getHallCapacity());
    }
    getHallCapacity()
    {
        this.proService.getProduktionshallenkapazitaet().subscribe(data=>{
            this.frei = data.free;
            this.gesamt = data.gesamt;
        },);
    }
}