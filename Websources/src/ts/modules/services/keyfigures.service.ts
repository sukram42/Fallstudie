/**
 * Created by boebel on 13.01.2017.
 */

import {Injectable} from '@angular/core';
import {Http} from "@angular/http";

import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

@Injectable()
export class KeyFiguresService {
    constructor(private _http: Http) {
        this._http = _http;
    }

    getSoftKeyFigure(name:string)
    {
        return  Observable.interval(10000).flatMap(()=>this._http.get('http://localhost:8080/rest/companies/keyfigures/soft/' + name)
            .map(response => response.json()));
    }
    getSoftKeyFigures()
    {
        return  Observable.interval(1000).flatMap(()=>this._http.get('http://localhost:8080/rest/companies/keyfigures/soft')
        .map(response => response.json()));
    }

    getKeyFigures()
    {
        return this._http.get('http://localhost:8080/rest/companies/keyfigures').map(res=>res.json());
    }

    getBilanz()
    {
        return this._http.get('http://localhost:8080/rest/companies/keyfigures/bilanz').map(res=>res.json());
    }

    getEmployeeCount()
    {
        return Observable.interval(5000).flatMap(()=>this._http.get('http://localhost:8080/rest/companies/employees/count')
            .map(response => response.text()));
    }

    getLiquideMittel()
    {
        return Observable.interval(1000).flatMap(()=>this._http.get('http://localhost:8080/rest/companies/keyfigures/bilanz')
            .map(res=>res.json()));
    }


}
