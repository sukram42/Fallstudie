import {Http} from "@angular/http";
import {Subject} from "rxjs/Subject";
import {Observable} from "rxjs/Observable";
import {Injectable} from "@angular/core";
/**
 * Created by boebel on 24.01.2017.
 */


@Injectable()
export class SalesService {

    private mockSubject = new Subject<any>();


    constructor(private http: Http) {
    }

    getAusschreibungen()
    {
        // return Observable.interval(1000).flatMap(()=>this.http.get('http://localhost:8080/rest/ausschreibungen').map(response => response.json()));
        return this.http.get('http://localhost:8080/rest/ausschreibungen').map(response => response.json());

    }
}
