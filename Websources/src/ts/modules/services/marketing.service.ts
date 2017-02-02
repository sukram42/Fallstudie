/**
 * Created by boebel on 02.02.2017.
 */

import {Injectable} from '@angular/core';
import {Http} from "@angular/http";

import 'rxjs/Rx';
import {Subject} from "rxjs/Subject";

@Injectable()
export class MarketingService {
    
    campaigneSubject : Subject<any>;
    
    constructor(private http: Http) {
    }

    startCampaign(art,laufzeit)
    {
        return this.http.post('http://localhost:8080/rest/companies/marketing/campaigns/' + art, laufzeit)
            .map(res => res.text())
            .subscribe(data=>{
                if(data.toString().startsWith("ERROR"))
                    this.campaigneSubject.next(data);
                else this.campaigneSubject.next("Kampagne gestartet")

            });
    }

    stopCampaign(art)
    {
        return this.http.delete('http://localhost:8080/rest/companies/marketing/campaigns/' + art).map(res => res.text());
    }
    isCampaignAktive(art)
    {
        return this.http.get('http://localhost:8080/rest/companies/marketing/campaigns/' + art).map(res=>res.text());
    }
}
