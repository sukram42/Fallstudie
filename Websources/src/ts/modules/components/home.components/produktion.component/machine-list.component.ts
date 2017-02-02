/**
 * Created by boebel on 20.01.2017.
 */


import {ProduktionService} from "../../../services/produktion.service";
import {Component} from "@angular/core";
import {HomeService} from "../../../services/home.service";

@Component({
    selector: 'machine-list-component',
    templateUrl: '../../../../templates/components/home.components/produktion.component/machine-list.component.html',
})

export class MachineListComponent {
    data;

    constructor(private proService: ProduktionService,private homeService : HomeService) {
        this.loadList();
        proService.getMachinesSubject().asObservable().subscribe(data=>this.loadList());

        this.homeService.getTime().subscribe(()=>this.refreshStatus());
    }

    loadList()
    {
        this.proService.getMachines()
            .subscribe(data=>{this.data = data});
    }

    refreshStatus()
    {
        if(this.data && this.data.length > 0) {
            for (var i = 0; i < this.data.length; i++) {
                this.proService.getMachinesStatus(i)
                    .subscribe(data => {
                        if(this.data[i])
                           this.data[i].status = data;
                    });
            }
        }
    }

    repairMachine(index)
    {
        this.proService.repairMachines(index).subscribe(
            data=>console.log(data),
            err=>console.log(err),
            ()=>this.loadList()
        );
    }

}