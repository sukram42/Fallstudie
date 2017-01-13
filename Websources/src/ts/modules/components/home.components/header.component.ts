/**
 * Created by boebel on 04.01.2017.
 */

/**
 * Created by boebel on 04.01.2017.
 */

import { Component } from '@angular/core';
import {HomeService} from "../../services/home.service";

@Component({
    selector: 'header-component',
    templateUrl: '../../../../templates/components/home.components/header.component.html',
    providers : [HomeService]
})

export class HeaderComponent {
    companyName;
    time;
    mitarbeiterCount;

    constructor(private homeService : HomeService){
        this.init();
    }

    init()
    {
        this.homeService.getTime().subscribe(data=>this.time = data);
        this.homeService.getCompany()
            .subscribe(data => this.companyName = data.name,
                err => alert(err));
        this.homeService.getEmployeeCount().subscribe(data=>this.mitarbeiterCount = data);
    }

}