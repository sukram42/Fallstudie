import { Component } from '@angular/core';
import {LoginFormularService} from "../services/login-formular.service";

@Component({
  selector: 'login-formular',
  templateUrl: '../../../../templates/components/login-formular.component.html',
  providers: [LoginFormularService]
})

export class LoginFormComponent {

  constructor(private _logFormService: LoginFormularService){}

  creds = {username:""
          ,password:""};

  token:string ;
  error:boolean ;

  anmeldenClicked()
  {
      this._logFormService.requestToken(this.creds.username,this.creds.password)
          .subscribe(data =>this.token = data,
                     err => this.error = true);
      if(!this.error) {
          window.localStorage.setItem("auth_key", this.token);
          console.log("TOKEN Saved : " + this.token);
          window.location.href = "";
      }

  }

}
