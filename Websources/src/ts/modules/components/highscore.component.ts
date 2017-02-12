/**
 * Created by boebel on 04.01.2017.
 */

import {Component, OnInit} from '@angular/core';
import {HomeService} from "../services/home.service";

@Component({
    selector: 'highscore-component',
    templateUrl: '../../../../templates/components/highscore.component.html',
    providers : [HomeService]
})

export class HighscoreComponent{

    highscore;

    constructor(private _homeService: HomeService)
    {
        this._homeService.getHighscore().subscribe(data => this.highscore = data);
    }



}
