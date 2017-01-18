
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


@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        HttpModule,
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
                   SozialeLeistungenComponent
    ],

    bootstrap:    [HomeComponent],
    providers : [HRService,
        {provide: RequestOptions, useClass : AuthRequestOptions}
    ]
})
export class HomeModule {
}
