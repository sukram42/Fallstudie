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

    constructor(private proService : ProduktionService, private fb :FormBuilder) {
        this.newMachine = fb.group({
            'product': [null, Validators.required],
            'klasse' : [null, Validators.required]
        });
    }

    submitForm(data)
    {
        console.log(data);
        this.proService.kaufeMaschine(data).subscribe(data=>console.log(data),err=>console.log(err));
    }
}