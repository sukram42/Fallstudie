/**
 * Created by boebel on 09.01.2017.
 */


import { Component } from '@angular/core';
import {ResearchService} from "../../../services/research.service";
import {HomeService} from "../../../services/home.service";

@Component({
    selector   : 'research-list',
    templateUrl: '../../../../templates/components/home.components/research.component/research-list.component.html',

})

export class ResearchListComponent {

    active;
    constructor(private _homeService:HomeService,private _researchService:ResearchService)
    {
        this.loadList();
        this._researchService.getResearchSubject().subscribe(()=>this.loadList());
        this._homeService.getTime().subscribe(()=>this.loadList());
    }
    loadList()
    {
        this._researchService.getProjects().subscribe(data=>this.active = data);
    }
}