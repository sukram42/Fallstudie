/**
 * Created by boebel on 02.02.2017.
 */


import { Component } from '@angular/core';
import {FormGroup, FormBuilder, Validators} from "@angular/forms";
import {MarketingService} from "../../../services/marketing.service";
import {HomeService} from "../../../services/home.service";

@Component({
    selector   : 'campaign-list-component',
    templateUrl: '../../../../templates/components/home.components/marketing.component/campaign-list.component.html',

})
export class CampagneListComponent {
    campaigns;

    constructor(private _homeService:HomeService, private _markService:MarketingService)
    {
        this.loadList();
        _markService.getCampaignSubject().subscribe(()=>this.loadList())
        _homeService.getTime().subscribe(()=>this.loadList());
    }

    loadList()
    {
        this._markService.getCampaigns().subscribe(data=>{this.campaigns = data});
    }



}