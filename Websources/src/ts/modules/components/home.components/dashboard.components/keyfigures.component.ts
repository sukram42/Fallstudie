/**
 * Created by boebel on 10.01.2017.
 */

/**
 * Created by boebel on 04.01.2017.
 */

import {Component, Input, OnInit} from '@angular/core';
import {HomeService} from "../../../services/home.service";

@Component({
    selector: 'kennzahl',
    templateUrl: '../../../../templates/components/home.components/dashboard.components/keyfigures.component.html',
    providers: [HomeService]
})

export class KeyfiguresComponent implements OnInit{
  

   // @Input('progress') progress: number;
    progress = 0;
    @Input('title') title: string

    constructor(private homeService : HomeService){

    }
    ngOnInit(): void{
        var newTitle = this.title.toLowerCase()
            .replace("ä","ae")
            .replace("ü","ue")
            .replace("ö","oe")
            .replace("ß","ss");

        this.homeService.getKeyFigure(newTitle)
            .subscribe(data =>(this.progress = Math.round((data*100) * 100) / 100),
                       err=>console.log(err));
    }

}