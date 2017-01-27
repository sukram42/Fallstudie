/**
 * Created by boebel on 12.01.2017.
 */
/**
 * Created by boebel on 09.01.2017.
 */

/**
 * Created by boebel on 04.01.2017.
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

    constructor(private hrService : HRService, private fb :FormBuilder){

        this.newEmployee = fb.group({
            // We can set default values by passing in the corresponding value or leave blank if we wish to not set the value. For our example, we’ll default the gender to female.
            'anzahl' :    [null, Validators.compose([Validators.required,Validators.pattern("[0-9]+")])],
            'gehalt':     [null ,Validators.compose([Validators.required,Validators.pattern("[0-9]+"), maximumWageValidator(1360)])],
            'abteilung':  [null, Validators.required]
        });

    }

    submitForm(value: any){
        this.hrService.addEmployees({anzahl:value.anzahl,gehalt:value.gehalt,abteilung:value.abteilung});
    }



}