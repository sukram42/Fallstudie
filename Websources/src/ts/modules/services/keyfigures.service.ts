/**
 * Created by boebel on 13.01.2017.
 */
/**
 * Created by boebel on 04.01.2017.
 */
/**
 * Created by boebel on 04.01.2017.
 */
import {Injectable} from '@angular/core';
import {Http} from "@angular/http";

import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

@Injectable()
export class KeyFiguresService {
    constructor(private http: Http) {
        this.http = http;
    }

    getKeyFigure(name:string)
    {
        return  Observable.interval(10000).flatMap(()=>this.http.get('http://localhost:8080/rest/companies/keyfigures/soft/' + name)
            .map(response => response.json()));
    }
    getKeyFigures()
    {
        return  Observable.interval(1000).flatMap(()=>this.http.get('http://localhost:8080/rest/companies/keyfigures/soft')
        .map(response => response.json()));
    }


    getEmployeeCount()
    {
        return Observable.interval(5000).flatMap(()=>this.http.get('http://localhost:8080/rest/companies/employees/count')
            .map(response => response.text()));
    }

    getLiquideMittel()
    {
        return Observable.interval(1000).flatMap(()=>this.http.get('http://localhost:8080/rest/companies/keyfigures/bilanz')
            .map(res=>res.json()));
    }


}
