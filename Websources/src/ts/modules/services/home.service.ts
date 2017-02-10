/**
 * Created by boebel on 04.01.2017.
 */
import {Injectable, EventEmitter} from '@angular/core';
import {Http, Headers} from "@angular/http";

import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

@Injectable()
export class HomeService {
    constructor(private http: Http) {
        this.http = http;
    }



    getCompany() {
        let data;
        return this.http.get('http://localhost:8080/rest/companies')
            .map(response => response.json());
    }

    getTime()
    {
        return Observable.interval(1000).flatMap(()=>this.http.get('http://localhost:8080/rest/time').map(response => response.text()));
    }

    isBankrupt()
    {
        return Observable.interval(1000).flatMap(()=>this.http.get('http://localhost:8080/rest/companies/bankrupt')).map(response=>response.text());
    }


    getToken() {
        return window.localStorage.getItem("auth_key");
    }

    validateToken() {
        var headers = new Headers();

        console.log(this.getToken());
        if (this.getToken())
            headers.append("Authorization", "Bearer " + this.getToken());
        else return null;

        return this.http.get('http://localhost:8080/rest/auth', {headers})
            .map(response => response.text());
    }
    logOut()
    {
        return this.http.put("http://localhost:8080/rest/logout",null).map(res=>res.text());
    }

    getMarktanteile()
    {
        return this.http.get("http://localhost:8080/rest/players").map(res=>res.json());
    }
}
