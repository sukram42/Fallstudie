/**
 * Created by boebel on 26.01.2017.
 */

import { Component } from '@angular/core';
import {SalesService} from "../../services/sales.service";

@Component({
    selector   : 'finances-component',
    templateUrl: '../../../../templates/components/home.components/finances.component.html',

})

export class FinancesComponent {

    errorL = false;
    errorV = false;
    errorM = false;
    errorB = false;

    constructor(private _salesService:SalesService)
    {
        _salesService.getCreditSubject().subscribe(data=>{
            if(data.startsWith("ERROR:L"))
                this.errorL = true;
            else if(data.startsWith("ERROR:V"))
                this.errorV = true;
            else if(data.startsWith("ERROR:M"))
                this.errorM = true;
            else if(data.startsWith("ERROR:B"))
                this.errorB = true;
            else
            {
                this.errorL = false;
                this.errorV = false;
                this.errorM = false;
                this.errorB = false;
            }
        });
    }
}
