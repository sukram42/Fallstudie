/**
 * Created by boebel on 24.01.2017.
 */

import {Component} from "@angular/core";
import {SalesService} from "../../../services/sales.service";
import {HomeService} from "../../../services/home.service";

@Component({
    selector: 'accounts-list-component',
    templateUrl: '../../../../templates/components/home.components/sales.component/accounts-list.component.html',
})

export class AccountsListComponent {
    accounts;
    constructor(private salesService: SalesService, private _homeService:HomeService) {
        this.loadList()
        this._homeService.getTime().subscribe(data=>this.loadList());
    }
    loadList()
    {
        this.salesService.getAccounts().subscribe(data=>{this.accounts = data},err=>console.log(err));
    }
}