/**
 * Created by boebel on 09.01.2017.
 */


import { Component } from '@angular/core';
import {HRService} from "../../services/hr.service";
import {ResearchService} from "../../services/research.service";

@Component({
    selector   : 'home-component',
    templateUrl: '../../../../templates/components/home.components/research.component.html',

})

export class ResearchComponent {

    employees;
    error = false;
    constructor(private _hrService:HRService, private _researchService:ResearchService) {
        _hrService.getEmployeesResearch().subscribe(
            data => {
                this.employees = data, console.log(data)
            }
        );
        _researchService.getResearchSubject().subscribe(data=>{
            if(data.startsWith("ERROR:M"))
                this.error = true;
            else this.error = false;
        });

    }
}