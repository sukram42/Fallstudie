/**
 * Created by boebel on 04.01.2017.
 */

/**
 * Created by boebel on 04.01.2017.
 */

import {Component, OnInit} from '@angular/core';
import {HomeService} from "../../services/home.service";
import {KeyFiguresService} from "../../services/keyfigures.service";
import {HRService} from "../../services/hr.service";

@Component({
    selector: 'header-component',
    templateUrl: '../../../../templates/components/home.components/header.component.html',
    providers : [KeyFiguresService,HomeService,HRService]
})

export class HeaderComponent implements OnInit{

    companyName;
    time;
    mitarbeiterCount;

    constructor(private homeService :HomeService, private keyFigures : KeyFiguresService, private hrService : HRService){
        this.init();
        this.homeService.getCompany()
            .subscribe(data => this.companyName = data.name,
                err => alert(err));
    }

    init()
    {
        this.homeService.getTime().subscribe(data=>this.time = data);
        this.keyFigures.getEmployeeCount().subscribe(data=>this.mitarbeiterCount = data);
    }
    ngOnInit(): void {
    }

}