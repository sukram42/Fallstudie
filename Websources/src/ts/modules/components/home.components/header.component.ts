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

    bankrupt=false;

    constructor(private _homeService :HomeService, private _keyFigures : KeyFiguresService, private _hrService : HRService, private _proService : ProduktionService){
        this.init();
        this._homeService.getCompany()
            .subscribe(data => this.companyName = data.name,
                err => alert(err));

        _homeService.isBankrupt().subscribe(data=>{
            if(data =="true")this.bankrupt =true;
        });

        this._proService.getWarehouseSubject()
            .asObservable().subscribe(data=>this.getCapacity(),err=>console.log(err));
        this._hrService.getEmployeeSubject().subscribe(data=>{this.getEmployeeCount()},err=>{})
    }

    init()
    {
        this.getCapacity();
        this.getEmployeeCount();
        this._homeService.getTime().subscribe(data=>this.time = data);
        this.getLiquideMittel();
    }

    getEmployeeCount()
    {
        this._keyFigures.getEmployeeCount().subscribe(data=>this.mitarbeiterCount = data);
    }

    getCapacity() {
        this._proService.getLagerkapazitaet().subscribe(data=>{
            this.freieLagerPlaetze = data.free;
            this.gesamteLagerPlaetze = data.gesamt;
        });
        this._proService.getLagerkapazitaetIntervall().subscribe(data => {
            this.freieLagerPlaetze = data.free;
            this.gesamteLagerPlaetze = data.gesamt;
        });
    }


    getLiquideMittel()
    {
        this._keyFigures.getLiquideMittel().subscribe(data=>this.liquideMittel = data.liquideMittel,err=>console.log(err));
    }

    logOutRequest()
    {
        this._homeService.logOut().subscribe(data=>data, err=>console.log(err),()=>this.logOut());
    }
    logOut()
    {
        window.localStorage.removeItem("auth_key");
        window.location.href = "index.html";
    }

}