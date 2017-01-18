/**
 * Created by boebel on 17.01.2017.
 */

import {Component} from '@angular/core';
import {FormControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HRService} from "../../../services/hr.service";

@Component({
    selector: 'soziale-leistungen',
    templateUrl: '../../../../templates/components/home.components/human-ressources.component/social-project.component.html',

})

export class SozialeLeistungenComponent {

    socialProjects;


    constructor(private hrService : HRService){
        this.init();
        this.hrService.getSocialProjectSubject().subscribe(data=>this.init());
    }
    init()
    {
        this.hrService.getSocialProject().subscribe(data=>{this.socialProjects = data,console.log(data)});
    }

    changeProjectProject(name)
    {
        this.hrService.changeProjectActivity(name);
    }

}