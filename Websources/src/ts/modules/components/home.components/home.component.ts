
/**
 * Created by boebel on 04.01.2017.
 */

import { Component } from '@angular/core';
import {HomeService} from "../../services/home.service";

@Component({
    selector   : 'home-component',
    templateUrl: '../../../../templates/components/home.components/home.component.html',

})

export class HomeComponent {

    constructor(private _homeService:HomeService)
    {
        _homeService.isBankrupt().subscribe(data=>
        {
           if(data =="true")
           {
               console.error("BANKROT!");
           }
        });
    }
}