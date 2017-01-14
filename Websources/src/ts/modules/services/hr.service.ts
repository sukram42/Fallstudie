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
import {Http} from "@angular/http"
import {Subject} from "rxjs/Subject";
import {Observable} from "rxjs/Observable";

@Injectable()
export class HRService {

    private empSubject = new Subject<any>();

    constructor(private http: Http) {
    }

    addEmployees(values)
    {
        this.http.post('http://localhost:8080/rest/companies/employees',values).subscribe(data=>{this.empSubject.next(values)},err=>this.empSubject.error(err));
    }

    getEmployees() {
        return this.http.get('http://localhost:8080/rest/companies/employees').map(res => res.json());
    }


    getEmployeeSubject(): Observable<any> {
        return this.empSubject.asObservable();
    }

}
