/**
 * Created by boebel on 10.02.2017.
 */
import {Http} from "@angular/http";
import {Subject} from "rxjs/Subject";
import {Injectable} from "@angular/core";


@Injectable()
export class ResearchService {

    private researchSubject = new Subject<any>();


    constructor(private _http: Http) {
    }

    starteProjekt(value:{})
    {
        this._http.post('http://localhost:8080/rest/companies/research/projects',value)
            .map(res=>res.text())
            .subscribe(data=>this.researchSubject.next(data));
    }
    getResearchSubject()
    {
        return this.researchSubject.asObservable();
    }

    getProjects()
    {
        return this._http.get('http://localhost:8080/rest/companies/research/projects')
            .map(res=>res.json())
    }
    getOldProjects()
    {
        return this._http.get('http://localhost:8080/rest/companies/research/projects/finished')
            .map(res=>res.json())
    }
}
