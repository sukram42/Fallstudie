import {Http} from "@angular/http";
import {Subject} from "rxjs/Subject";
import {Observable} from "rxjs/Observable";
import {Injectable} from "@angular/core";
/**
 * Created by boebel on 24.01.2017.
 */


@Injectable()
export class SalesService {

    private opportunitiesSubject = new Subject<any>();
    private creditSubject = new Subject<any>();


    constructor(private http: Http) {
    }

    getAusschreibungen()
    {
        //return Observable.interval(1000).flatMap(()=>this.http.get('http://localhost:8080/rest/companies/sales/ausschreibungen').map(response => response.json()));
        return this.http.get('http://localhost:8080/rest/companies/sales/ausschreibungen').map(response => response.json());

    }
    getOpportunities()
    {
        // return Observable.interval(1000).flatMap(()=>this.http.get('http://localhost:8080/rest/ausschreibungen').map(response => response.json()));
        return this.http.get('http://localhost:8080/rest/companies/sales/opportunities').map(response => response.json());
    }
    getOpportunitiesSubject()
    {
        return this.opportunitiesSubject.asObservable();
    }
    bewerben(index)
    {
        return this.http.post('http://localhost:8080/rest/companies/sales/ausschreibungen',index)
            .map(response => response.text())
            .subscribe(data=>this.opportunitiesSubject.next(data),err=>this.opportunitiesSubject.error(err));
    }
    getAccounts()
    {
        // return Observable.interval(1000).flatMap(()=>this.http.get('http://localhost:8080/rest/ausschreibungen').map(response => response.json()));
        return this.http.get('http://localhost:8080/rest/companies/sales/accounts').map(response => response.json());
    }
    requestKredit(values:{})
    {
        return this.http.post('http://localhost:8080/rest/companies/sales/credits',values)
            .map(response=> response.text())
            .subscribe(data=>this.creditSubject.next(data));
    }

    getCredits()
    {
        return this.http.get('http://localhost:8080/rest/companies/sales/credits')
            .map(response=> response.json());
    }

    getCreditSubject()
    {
        return this.creditSubject.asObservable();
    }
}
