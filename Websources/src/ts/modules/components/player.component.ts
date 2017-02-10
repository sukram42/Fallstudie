/**
 * Created by boebel on 04.01.2017.
 */

import {Component, OnInit} from '@angular/core';
import {HomeService} from "../services/home.service";

@Component({
    selector: 'player-component',
    templateUrl: '../../../../templates/components/player.component.html',
    providers : [HomeService]
})

export class PlayerComponent{

    marktanteile;

    constructor(private _homeService: HomeService)
    {
        this.getPlayers()
    }

    getPlayers()
    {
        this._homeService.getMarktanteile().subscribe(data=>{this.marktanteile = data,console.log(data)});
    }


}
