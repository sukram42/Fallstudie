
/**
 * Created by boebel on 04.01.2017.
 */
import {Injectable, Inject} from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';
import {LoginFormularService} from "./login-formular.service";


@Injectable()
export class RegisterFormularService {
    logInService;

    constructor(private http: Http, logInService:LoginFormularService) {
        this.logInService =logInService;
    }

    createCompany(companyname:string, password:string) {
        var params = {name: companyname, passwort: password};
        var headers = new Headers();
        // headers.append('Content-Type','application/x-www-form-urlencoded');
        return this.http.post('http://localhost:8080/Fallstudie-0.0.1-SNAPSHOT/rest/companies', params, {headers})
            .map(response => response.text());
    }


}
