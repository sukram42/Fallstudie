/**
 * Created by boebel on 09.01.2017.
 */


import { Component } from '@angular/core';
import {ResearchService} from "../../../services/research.service";
import {HomeService} from "../../../services/home.service";

@Component({
    selector   : 'old-research-list',
    templateUrl: '../../../../templates/components/home.components/research.component/old-research-list.component.html',

})

export class OldResearchListComponent {

    old;
    constructor(private _homeService:HomeService,private _researchService:ResearchService)
    {
        this.loadList();
        this._researchService.getResearchSubject().subscribe(()=>this.loadList());
        this._homeService.getTime().subscribe(()=>
        {
            // this.loadList()
            this._researchService.getOldProjects().subscribe(data=>
            {
                if(this.old.length !=data.length)
                    this.old = data
            });
        });
    }
    loadList()
    {
        this._researchService.getOldProjects().subscribe(data=>
        {
            if(data !== this.old)
                 this.old = data
        });
    }
}