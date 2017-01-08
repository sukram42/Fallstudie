/**
 * Created by boebel on 04.01.2017.
 */

import {BaseRequestOptions} from "@angular/http";


export class AuthRequestOptions extends BaseRequestOptions {

    constructor() {
        super();
        this.headers.append('Authorization', 'Bearer ' + this.getToken());
    }

    private getToken() {
        return window.localStorage.getItem("auth_key");
    }
}