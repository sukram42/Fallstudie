/**
 * Created by boebel on 02.02.2017.
 */

import {Injectable} from '@angular/core';
import {Http} from "@angular/http";

import 'rxjs/Rx';
import {Subject} from "rxjs/Subject";

@Injectable()
export class MarketingService {

    campaignSubject = new Subject<any>();
    
    constructor(private http: Http) {
    }

    startCampaign(art,laufzeit)
    {
        return this.http.post('http://localhost:8080/rest/companies/marketing/campaigns/' + art, laufzeit)
            .map(res => res.text())
            .subscribe(data=>{
                console.log("DATA: " + data);
                if(data.toString().startsWith("ERROR"))
                    this.campaignSubject.next(data);
                else this.campaignSubject.next("Kampagne gestartet")
            });
    }

    getCosts()
    {
        return this.http.get('http://localhost:8080/rest/companies/marketing/costs').map(res=>res.json());
    }

    getCampaignSubject()
    {
        return this.campaignSubject.asObservable();
    }
    getCampaigns()
    {
        return this.http.get('http://localhost:8080/rest/companies/marketing/campaigns')
            .map(res => res.json())
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
