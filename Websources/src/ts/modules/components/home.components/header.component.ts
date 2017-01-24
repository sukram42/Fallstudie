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
import {ProduktionService} from "../../services/produktion.service";

@Component({
    selector: 'header-component',
    templateUrl: '../../../../templates/components/home.components/header.component.html'
})

export class HeaderComponent{

    companyName;
    time;
    mitarbeiterCount;

    freieLagerPlaetze;
    gesamteLagerPlaetze;
    liquideMittel;

    constructor(private homeService :HomeService, private keyFigures : KeyFiguresService, private hrService : HRService, private proService : ProduktionService){
        this.init();
        this.homeService.getCompany()
            .subscribe(data => this.companyName = data.name,
                err => alert(err));

        this.proService.getWarehouseSubject()
            .asObservable().subscribe(data=>this.getCapacity(),err=>console.log(err));

        this.hrService.getEmployeeSubject().subscribe(data=>{this.getEmployeeCount()},err=>console.log(err))
    }

    init()
    {
        this.getCapacity();
        this.getEmployeeCount();
        this.homeService.getTime().subscribe(data=>this.time = data);
        this.getLiquideMittel();
    }

    getEmployeeCount()
    {
        this.keyFigures.getEmployeeCount().subscribe(data=>this.mitarbeiterCount = data);
        console.log("hallo");
    }

    getCapacity() {
        this.proService.getLagerkapazitaet().subscribe(data => {
            this.freieLagerPlaetze = data.free;
            this.gesamteLagerPlaetze = data.gesamt;
        });
    }


    getLiquideMittel()
    {
        this.keyFigures.getLiquideMittel().subscribe(data=>this.liquideMittel = data.liquideMittel,err=>console.log(err));
    }

    logOutRequest()
    {
        this.homeService.logOut().subscribe(data=>data, err=>console.log(err),()=>this.logOut());
    }
    logOut()
    {
        window.localStorage.removeItem("auth_key");
        window.location.href = "index.html";
    }

}