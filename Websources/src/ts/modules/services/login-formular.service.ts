/**
 * Created by boebel on 04.01.2017.
 */
import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';


@Injectable()
export class LoginFormularService {

    constructor(private http: Http) { }

    requestToken(username:string, password:string)
    {
        var params = "username=" + username +"&password=" + password;
        var headers = new Headers();
        headers.append('Content-Type','application/x-www-form-urlencoded');
        return this.http.post('http://localhost:8080/Fallstudie-0.0.1-SNAPSHOT/rest',params,{headers})
            .map(response => response.text());
    }


}
