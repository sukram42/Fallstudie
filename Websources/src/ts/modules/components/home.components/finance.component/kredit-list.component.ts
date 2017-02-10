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
    selector: 'kredit-list-component',
    templateUrl: '../../../../templates/components/home.components/finance.component/kredit-list.component.html',
})

export class KreditListComponent {
    credits;
    constructor(private _financeService:SalesService)
    {
        this.loadList();
        this._financeService.getCreditSubject().subscribe(data=>this.loadList());
    }
    loadList()
    {
        this._financeService.getCredits().subscribe(data=>{this.credits = data,console.log(data)});
    }

}