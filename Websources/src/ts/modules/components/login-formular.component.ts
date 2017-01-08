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

  token:String ;

  anmeldenClicked()
  {
      this._logFormService.requestToken(this.creds.username,this.creds.password);
  }

}
