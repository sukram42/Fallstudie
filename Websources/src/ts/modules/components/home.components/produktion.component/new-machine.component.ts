/**
 * Created by boebel on 19.01.2017.
 */


import {Component} from '@angular/core';
import {FormControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProduktionService} from "../../../services/produktion.service";

@Component({
    selector: 'new-machine-modal',
    templateUrl: '../../../../templates/components/home.components/produktion.component/new-machine.component.html',
})

export class NewMachineComponent {

    newMachine:FormGroup;
    costs;

    constructor(private _proService : ProduktionService, private fb :FormBuilder) {
        this.newMachine = fb.group({
            'product': [null, Validators.required],
            'klasse' : [null, Validators.required]
        });
        this._proService.getStaticMachinesEnergyCosts().subscribe(data=>this.costs=data);
    }



    submitForm(data)
    {
        console.log(data);
        this._proService.kaufeMaschine(data);
    }
}