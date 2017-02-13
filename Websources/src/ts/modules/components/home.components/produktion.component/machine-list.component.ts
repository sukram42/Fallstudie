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

    constructor(private _proService: ProduktionService,private homeService : HomeService) {
        this.loadList();
        _proService.getMachinesSubject().asObservable().subscribe(data=>this.loadList());

        this.homeService.getTime().subscribe(()=>this.refreshStatus());
    }

    loadList()
    {
        this._proService.getMachines()
            .subscribe(data=>this.data = data);
    }

    refreshStatus()
    {
        this._proService.getMachines()
            .subscribe(data=>
            {
                for(var i = 0; i<data.length;i++)
                {
                    this.data[i].status = data[i].status;
                }
            });
    }

    repairMachine(index)
    {
        this._proService.repairMachines(index).subscribe(
            ()=>{},
            err=>console.log(err),
            ()=>this.loadList()
        );
    }

    sellMachine(index)
    {
        this._proService.sell(index);
    }

    roundStatus(machine)
    {
        return Math.round(machine.status*100);
    }
}