/**
 * Created by boebel on 02.02.2017.
 */
import {Component} from "@angular/core";
import {SalesService} from "../../../services/sales.service";

@Component({
    selector: 'accounts-sidebar-component',
    templateUrl: '../../../../templates/components/home.components/sales.component/accounts-sidebar.component.html',
})

export class AccountsSidebar {
    accounts;
    ausbringung;
    bestand;
    constructor(private _salesService : SalesService)
    {
        this._salesService.getAccounts().subscribe(data=>
        {
            this.accounts = data;
        });
        this._salesService.getAusbringung().subscribe(data=>
        {
            this.ausbringung = data;
        });
        this._salesService.getBestand().subscribe(data=>
        {
            this.bestand = data;
        });

    }
}