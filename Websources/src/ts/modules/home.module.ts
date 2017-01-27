/**
 * Created by boebel on 04.01.2017.
 */


import {NgModule}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule, RequestOptions} from '@angular/http';
import {RouterModule} from "@angular/router";


import {HomeComponent} from "./components/home.components/home.component";
import {HeaderComponent} from "./components/home.components/header.component";
import {AuthRequestOptions} from "./services/auth-request-options";

import {DashboardComponent} from "./components/home.components/dashboard.component";
import {SalesComponent} from "./components/home.components/sales.component";
import {ResearchComponent} from "./components/home.components/research.component";
import {ProduktionComponent} from "./components/home.components/produktion.component";
import {MarketingComponent} from "./components/home.components/marketing.component";
import {HRComponent} from "./components/home.components/human-ressources.component";
import {AccountsComponent} from "./components/home.components/accounts.component";
import {KeyfiguresComponent} from "./components/home.components/dashboard.components/keyfigures.component";
import {NewEmployeeComponent} from "./components/home.components/human-ressources.component/new-employee.component";
import {EmployeeListComponent} from "./components/home.components/human-ressources.component/employee-list.component";
import {HRService} from "./services/hr.service";
import {SozialeLeistungenComponent} from "./components/home.components/human-ressources.component/soziale-leistungen.component";

import {ChartModule} from 'angular2-chartjs';
import {ProduktionService} from "./services/produktion.service";
import {NewProductLineComponent} from "./components/home.components/produktion.component/new-product-line.component";
import {KeyFiguresService} from "./services/keyfigures.service";
import {NewMachineComponent} from "./components/home.components/produktion.component/new-machine.component";
import {NewWarehouseComponent} from "./components/home.components/produktion.component/new-warehouse.component";
import {NewProductionHallComponent} from "./components/home.components/produktion.component/new-production-hall.component";
import {ProductLineComponent} from "./components/home.components/produktion.component/productline-list.component";
import {MachineListComponent} from "./components/home.components/produktion.component/machine-list.component";
import {HomeService} from "./services/home.service";
import {AusschreibungListComponent} from "./components/home.components/sales.component/ausschreibungen-list.component";
import {SalesService} from "./services/sales.service";
import {OpportunitiesListComponent} from "./components/home.components/sales.component/opportunities-list.component";
import {AccountsListComponent} from "./components/home.components/sales.component/accounts-list.component";
import {FinancesComponent} from "./components/home.components/finances.component";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        HttpModule,
        ChartModule,
        RouterModule.forRoot([

            {
                path: 'dashboard',
                component: DashboardComponent
            },
            {
                path: 'sales',
                component: SalesComponent
            }
            ,
            {
                path: 'research',
                component: ResearchComponent
            },
            {
                path: 'produktion',
                component: ProduktionComponent
            },
            {
                path: 'marketing',
                component: MarketingComponent
            },
            {
                path: 'human-ressources',
                component: HRComponent
            },
            {
                path: 'accounts',
                component: AccountsComponent
            },
            {
                path: 'finances',
                component:FinancesComponent
            },
            {
                path: 'home.html',
                redirectTo: '/dashboard',
                pathMatch: 'full'
            }
        ])
    ],
    declarations: [HomeComponent,
        HeaderComponent,
        DashboardComponent,
        SalesComponent,
        ResearchComponent,
        ProduktionComponent,
        MarketingComponent,
        HRComponent,
        AccountsComponent,
        KeyfiguresComponent,
        NewEmployeeComponent,
        EmployeeListComponent,
        SozialeLeistungenComponent,
        NewProductLineComponent,
        NewMachineComponent,
        NewWarehouseComponent,
        NewProductionHallComponent,
        ProductLineComponent,
        MachineListComponent,
        AusschreibungListComponent,
        OpportunitiesListComponent,
        AccountsListComponent,
        FinancesComponent
    ],

    bootstrap: [HomeComponent],
    providers: [HRService, {provide: RequestOptions, useClass: AuthRequestOptions},
        ProduktionService, KeyFiguresService, HomeService, SalesService
    ]
})
export class HomeModule {
}
