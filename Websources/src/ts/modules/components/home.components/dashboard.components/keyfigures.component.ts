/**
 * Created by boebel on 10.01.2017.
 */

/**
 * Created by boebel on 04.01.2017.
 */

import {Component, Input, OnInit} from '@angular/core';
import {HomeService} from "../../../services/home.service";
import {KeyFiguresService} from "../../../services/keyfigures.service";
import {HRService} from "../../../services/hr.service";

@Component({
    selector: 'kennzahl',
    templateUrl: '../../../../templates/components/home.components/dashboard.components/keyfigures.component.html',
    providers: [HRService,KeyFiguresService]
})

export class KeyfiguresComponent implements OnInit{
  

   // @Input('progress') progress: number;
    progress = 50;
    @Input('title') title: string
    newTitle;


    constructor(private hrService : HRService, private keyFigures : KeyFiguresService){

    }
    ngOnInit(): void{
        this.newTitle = this.title.toLowerCase()
            .replace("ä","ae")
            .replace("ü","ue")
            .replace("ö","oe")
            .replace("ß","ss");
        this.init();

    }

    public init()
    {
        this.keyFigures.getKeyFigure(this.newTitle)
            .subscribe(data =>(this.progress = Math.round((data*100) * 100) / 100),
                err=>console.log(err));
    }
}