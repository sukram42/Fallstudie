/**
 * Created by boebel on 12.01.2017.
 */
/**
 * Created by boebel on 09.01.2017.
 */

/**
 * Created by boebel on 04.01.2017.
 */

import {Component, OnInit} from '@angular/core';
import {HRService} from "../../../services/hr.service";

@Component({
        selector: 'employee-list',
        templateUrl: '../../../../templates/components/home.components/human-ressources.component/employee-list.component.html'
    })

    export class EmployeeListComponent implements OnInit{

    mitarbeiterList = [];


    constructor(private hrService : HRService){

        hrService.getEmployeeSubject().subscribe((data) => this.setValues(),err=>{});
        this.setValues();
    }

    setValues()
    {
        this.hrService.getEmployees().subscribe(data=>this.mitarbeiterList = data,err=>{});
    }

    ngOnInit(): void {
    }

    fire(opfer)
    {
        this.hrService.fire(opfer);
    }






}