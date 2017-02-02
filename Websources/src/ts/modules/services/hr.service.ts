/**
 * Created by boebel on 13.01.2017.
 */

import {Injectable} from '@angular/core';
import {Http} from "@angular/http"
import {Subject} from "rxjs/Subject";
import {Observable} from "rxjs/Observable";

@Injectable()
export class HRService {

    private empSubject = new Subject<any>();
    private socialProjectsSubject = new Subject<any>();

    constructor(private http: Http) {
    }

    addEmployees(values)
    {
        return this.http.post('http://localhost:8080/rest/companies/employees',values)
            .map(res=>res.text())
            .subscribe(
                data=> {
                    if(data.toString().startsWith("ERROR")) this.empSubject.next(data);
                    else this.empSubject.next("neuer Mitarbeiter im Lande");
                });
}

    getEmployees() {
        return this.http.get('http://localhost:8080/rest/companies/employees').map(res => res.json());
    }

    getEmployeesProduktion()
    {
         return this.http.get('http://localhost:8080/rest/companies/employees/production').map(res => res.text());
    }

    getEmployeeSubject(): Observable<any> {
        return this.empSubject.asObservable();
    }

    getSocialProjectSubject():Observable<any>
    {
        return this.socialProjectsSubject.asObservable();
    }
    getSocialProject()
    {
        return this.http.get('http://localhost:8080/rest/companies/employees/socialprojects').map(res => res.json());
    }

    changeProjectActivity(name)
    {
        return this.http.put('http://localhost:8080/rest/companies/employees/socialprojects/',name).map(res => res.text())
            .subscribe(data=>{},err=>{console.log(err)},()=>this.socialProjectsSubject.next(name));
    }

    fire(opfer)
    {;
        var data = JSON.stringify(opfer)
        this.http.put('http://localhost:8080/rest/companies/employees',data).map(res=>res.text())
            .subscribe(data=>this.empSubject.next("Opfer detected and fired"),
                err=>this.empSubject.error(err));
    }
}
