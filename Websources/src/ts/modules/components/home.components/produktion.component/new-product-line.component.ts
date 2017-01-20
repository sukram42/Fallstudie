/**
 * Created by boebel on 18.01.2017.
 */


import {Component} from '@angular/core';
import {FormControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProduktionService} from "../../../services/produktion.service";

@Component({
    selector: 'new-product-line-modal',
    templateUrl: '../../../../templates/components/home.components/produktion.component/new-product-line.component.html',
})

export class NewProductLineComponent {

    newProduktLinie:FormGroup;

    constructor(private proService : ProduktionService, private fb :FormBuilder) {
        this.newProduktLinie = fb.group({
            'produkt' : [null, Validators.required],
            'quality' : [null, Validators.required],
            'menge'   : [null, Validators.compose([Validators.required,Validators.pattern("[0-9]+")])],
            'laufzeit': [null, Validators.compose([Validators.required,Validators.pattern("[0-9]+")])],
        });
    }

    submitForm(data)
    {
        this.proService.produzieren(data);
    }
}