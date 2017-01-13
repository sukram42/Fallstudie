/**
 * Created by boebel on 04.01.2017.
 */
/**
 * Created by boebel on 04.01.2017.
 */
import {Injectable} from '@angular/core';
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


    getKeyFigure(name:string)
    {
        return  Observable.interval(10000).flatMap(()=>this.http.get('http://localhost:8080/rest/companies/keyfigures/soft/' + name)
            .map(response => response.json()));
    }

    getTime()
    {
        return Observable.interval(5000).flatMap(()=>this.http.get('http://localhost:8080/rest/time').map(response => response.text()));
    }


    getToken() {
        console.log("Der Token ist : ")
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

    addEmployees(values)
    {

        console.log('hallo');
        this.http.post('http://localhost:8080/rest/companies/employees',values).subscribe();
    }
    getEmployeeCount()
    {
        return Observable.interval(5000).flatMap(()=>this.http.get('http://localhost:8080/rest/companies/employees/count')
            .map(response => response.text()));
    }

}
