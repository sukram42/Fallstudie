/**
 * Created by boebel on 04.01.2017.
 */
"use strict";
const http_1 = require("@angular/http");
class AuthRequestOptions extends http_1.BaseRequestOptions {
    constructor() {
        super();
        this.headers.append('Authorization', 'Bearer ' + this.getToken());
    }
    getToken() {
        return window.localStorage.getItem("auth_key");
    }
}
exports.AuthRequestOptions = AuthRequestOptions;
//# sourceMappingURL=auth-request-options.js.map