/**
 * Created by boebel on 20.01.2017.
 */


import {ProduktionService} from "../../../services/produktion.service";
import {Component} from "@angular/core";

@Component({
    selector: 'machine-list-component',
    templateUrl: '../../../../templates/components/home.components/produktion.component/machine-list.component.html',
})

export class MachineListComponent {
    data;

    constructor(private proService: ProduktionService) {
        this.loadList();
        proService.getMachinesSubject().asObservable().subscribe(data=>this.loadList());
    }

    loadList()
    {
        this.proService.getMachines()
            .subscribe(data=>{this.data = data,console.log(data)});
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