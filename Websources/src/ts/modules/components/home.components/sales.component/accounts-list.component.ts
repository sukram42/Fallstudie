/**
 * Created by boebel on 24.01.2017.
 */

import {Component} from "@angular/core";
import {SalesService} from "../../../services/sales.service";

@Component({
    selector: 'accounts-list-component',
    templateUrl: '../../../../templates/components/home.components/sales.component/accounts-list.component.html',
})

export class AccountsListComponent {
    accounts;

    constructor(private salesService: SalesService) {
        this.salesService.getAccounts().subscribe(data=>{this.accounts = data,console.log(data)},err=>console.log(err));
    }

}