
/**
 * Created by boebel on 04.01.2017.
 */

import { Component } from '@angular/core';
import {HomeService} from "../../services/home.service";

declare var $: any;

@Component({
    selector   : 'home-component',
    templateUrl: '../../../../templates/components/home.components/home.component.html',

})

export class HomeComponent {

    constructor(private _homeService:HomeService)
    {
        this._homeService.getTime().subscribe(()=>this.askForZuschlag());
    }
    askForZuschlag()
    {
        this._homeService.getNoZuschlagError().subscribe(data=>
        {
            if(+data>0)this.openNoZuschlagModal()
        });
    }
    openNoZuschlagModal()
    {
        $('#noZuschlagModal').modal('show')
    }
}