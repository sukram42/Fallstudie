/**
 * Created by boebel on 12.01.2017.
 */

import {Component} from '@angular/core';
import {FormControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HRService} from "../../../services/hr.service";
import {maximumWageValidator} from "../../../directives/min-wage.directive";

@Component({
    selector: 'new-employee-modal',
    templateUrl: '../../../../templates/components/home.components/human-ressources.component/new-employee.component.html',

})

export class NewEmployeeComponent {

    newEmployee:FormGroup;

    constructor(private _hrService : HRService, private fb :FormBuilder){

        this.newEmployee = fb.group({
           'anzahl' :    [null, Validators.compose([Validators.required,Validators.pattern("[0-9]+")])],
            'gehalt':     [null ,Validators.compose([Validators.required,Validators.pattern("[0-9]+"), maximumWageValidator(1360)])],
            'abteilung':  [null, Validators.required]
        });

    }

    submitForm(value: any){
        this._hrService.addEmployees({anzahl:value.anzahl,gehalt:value.gehalt,abteilung:value.abteilung});
    }
}