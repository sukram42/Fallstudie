/**
 * Created by boebel on 04.01.2017.
 */
/**
 * Created by boebel on 04.01.2017.
 */
import {Injectable} from '@angular/core';
import 'rxjs/add/operator/map';
import {Http, Headers} from "@angular/http";


@Injectable()
export class HomeService {
    constructor(private http: Http) {
        this.http = http;
    }

    getCompany() {
        let data;
        return this.http.get('http://localhost:8080/Fallstudie-0.0.1-SNAPSHOT/rest/companies')
            .map(response => response.json());

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


            return this.http.get('http://localhost:8080/Fallstudie-0.0.1-SNAPSHOT/rest/auth',{headers})
                .map(response => response.text());
//TODO weitermachen !
    }
}
