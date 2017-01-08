/**
 * Created by boebel on 04.01.2017.
 */
import {Component} from '@angular/core';
import {RegisterFormularService} from "../services/register-formular.service";
import {LoginFormularService} from "../services/login-formular.service";


@Component({
    selector: 'register-formular',
    templateUrl: '../../../../templates/components/register-formular.component.html',
    providers: [RegisterFormularService,LoginFormularService]
})
export class RegisterFormularComponent {
    input_companyName;
    input_password;
    input_password2;
    passworderror = false;

    constructor(private regService: RegisterFormularService,  private logInService: LoginFormularService) {
    }

    onRegisterClicked() {
        if (this.input_password == this.input_password2 && this.input_companyName != "") {
            this.regService.createCompany(this.input_companyName, this.input_password)
                .subscribe(data => this.logIn(this.input_companyName, this.input_password),
                    err => console.log("ERR" + err));
        }
        else
            this.passworderror = true;

    }

    logIn(companyname: string, password: string) {
        this.logInService.requestToken(companyname, password);
    }
}
