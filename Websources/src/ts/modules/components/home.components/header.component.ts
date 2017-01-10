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
    token;


    constructor(private homeService : HomeService){
        this.init();
    }

    init()
    {
        this.token = this.homeService.getToken();
        this.homeService.getCompany()
            .subscribe(data => this.companyName = data.name,
                err => alert(err));

    }

}