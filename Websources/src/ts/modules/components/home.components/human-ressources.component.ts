/**
 * Created by boebel on 09.01.2017.
 */

/**
 * Created by boebel on 04.01.2017.
 */

import { Component } from '@angular/core';
import {HRService} from "../../services/hr.service";

@Component({
    selector   : 'human-ressources-component',
    templateUrl: '../../../../templates/components/home.components/human-ressources.component.html',
})

export class HRComponent {

    error;
    constructor(hrService : HRService)
    {
        hrService.getEmployeeSubject().subscribe((data) => data,err=>this.error = err);
    }
}