/**
 * Created by boebel on 04.02.2017.
 */


import {Component, OnInit} from '@angular/core';
import {KeyFiguresService} from "../../../services/keyfigures.service";
import {HomeService} from "../../../services/home.service";

@Component({
    selector: 'actual-component',
    templateUrl: '../../../../templates/components/home.components/finance.component/actual.component.html',

})

export class ActualComponent implements OnInit{

    keyfigures;
    time;

    constructor(private _homeService: HomeService, private _keyfigService : KeyFiguresService)
    {}
    ngOnInit(): void {
        this.loadKonten();
        this._homeService.getTime().subscribe((data)=>{
            this.loadKonten();
            this.time = data;
        });
    }

    loadKonten() {
        this._keyfigService.getKeyFigures().subscribe(data => {
            this.keyfigures = data;
            console.log(data);
        });
    }

    runden(x)
    {
        return Math.round(x * 100) / 100 ;
    }

}