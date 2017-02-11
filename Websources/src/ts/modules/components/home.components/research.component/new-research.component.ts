/**
 * Created by boebel on 09.01.2017.
 */


import {Component} from '@angular/core';
import {ResearchService} from "../../../services/research.service";
import {FormGroup, Validators, FormBuilder} from "@angular/forms";

@Component({
    selector: 'new-research-component',
    templateUrl: '../../../../templates/components/home.components/research.component/new-research.component.html',

})

export class NewResearchComponent {


    newResearch: FormGroup;

    constructor(private _researchService: ResearchService, private fb: FormBuilder) {

        this.newResearch = fb.group({
            'product': [null, Validators.required],
            'quality':[null, Validators.required],
            'mitCount': [null, Validators.compose([Validators.required, Validators.pattern("[0-9]+")])],
            'dauer': [null, Validators.compose([Validators.required, Validators.pattern("[0-9]+")])],
            'herstellkosten': [null, Validators.required]
        });
    }
    submitForm(value: any) {
        this._researchService.starteProjekt(value);
    }
}