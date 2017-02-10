/**
 * Created by boebel on 04.02.2017.
 */


import {Component, OnInit} from '@angular/core';
import {KeyFiguresService} from "../../../services/keyfigures.service";
import {HomeService} from "../../../services/home.service";
import {FormGroup, FormBuilder, Validators} from "@angular/forms";
import {FinancesComponent} from "../finances.component";
import {SalesService} from "../../../services/sales.service";

@Component({
    selector: 'new-kredit-component',
    templateUrl: '../../../../templates/components/home.components/finance.component/new-kredit.component.html',

})

export class NewKreditComponent {
    newKredit:FormGroup;

    constructor(private _financeService:SalesService, private fb: FormBuilder)
    {
        this.newKredit = fb.group({
            'betrag' :    [null, Validators.compose([Validators.required,Validators.pattern("[0-9]+")])],
            'laufzeit':     [null ,Validators.compose([Validators.required,Validators.pattern("[0-9]+")])],
        });
    }
    submitForm(value: any){
        this._financeService.requestKredit({betrag:value.betrag,laufzeit:value.laufzeit});
        console.log("new Credit");
    }
}