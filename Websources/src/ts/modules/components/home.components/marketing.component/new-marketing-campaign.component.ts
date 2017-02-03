/**
 * Created by boebel on 02.02.2017.
 */


import {Component} from '@angular/core';
import {FormControl,FormGroup, FormBuilder, Validators} from "@angular/forms";
import {MarketingService} from "../../../services/marketing.service";

@Component({
    selector: 'new-marketing-campaign',
    templateUrl: '../../../../templates/components/home.components/marketing.component/new-marketing-campaign.component.html',

})
export class NewMarketingCampaignComponent {

    newCampaign: FormGroup;


    constructor(private marketing: MarketingService, private fb: FormBuilder) {

        this.newCampaign = fb.group({
            'art': [null, Validators.required],
            'laufzeit': [null, Validators.compose([Validators.required, Validators.pattern("[0-9]+")])],
        });
    }
    submitForm(value) {
        console.log("STARTE DAS ERSTELLEN VON MARKETING MASSNAHMEN");
        this.marketing.startCampaign(value.art, value.laufzeit);
    }

}