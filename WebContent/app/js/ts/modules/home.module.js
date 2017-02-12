/**
 * Created by boebel on 04.01.2017.
 */
"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
const core_1 = require("@angular/core");
const platform_browser_1 = require("@angular/platform-browser");
const forms_1 = require("@angular/forms");
const http_1 = require("@angular/http");
const router_1 = require("@angular/router");
const home_component_1 = require("./components/home.components/home.component");
const header_component_1 = require("./components/home.components/header.component");
const auth_request_options_1 = require("./services/auth-request-options");
const dashboard_component_1 = require("./components/home.components/dashboard.component");
const sales_component_1 = require("./components/home.components/sales.component");
const research_component_1 = require("./components/home.components/research.component");
const produktion_component_1 = require("./components/home.components/produktion.component");
const marketing_component_1 = require("./components/home.components/marketing.component");
const human_ressources_component_1 = require("./components/home.components/human-ressources.component");
const accounts_component_1 = require("./components/home.components/accounts.component");
const keyfigures_component_1 = require("./components/home.components/dashboard.components/keyfigures.component");
const new_employee_component_1 = require("./components/home.components/human-ressources.component/new-employee.component");
const employee_list_component_1 = require("./components/home.components/human-ressources.component/employee-list.component");
const hr_service_1 = require("./services/hr.service");
const soziale_leistungen_component_1 = require("./components/home.components/human-ressources.component/soziale-leistungen.component");
const angular2_chartjs_1 = require("angular2-chartjs");
const produktion_service_1 = require("./services/produktion.service");
const new_product_line_component_1 = require("./components/home.components/produktion.component/new-product-line.component");
const keyfigures_service_1 = require("./services/keyfigures.service");
const new_machine_component_1 = require("./components/home.components/produktion.component/new-machine.component");
const new_warehouse_component_1 = require("./components/home.components/produktion.component/new-warehouse.component");
const new_production_hall_component_1 = require("./components/home.components/produktion.component/new-production-hall.component");
const productline_list_component_1 = require("./components/home.components/produktion.component/productline-list.component");
const machine_list_component_1 = require("./components/home.components/produktion.component/machine-list.component");
const home_service_1 = require("./services/home.service");
const ausschreibungen_list_component_1 = require("./components/home.components/sales.component/ausschreibungen-list.component");
const sales_service_1 = require("./services/sales.service");
const opportunities_list_component_1 = require("./components/home.components/sales.component/opportunities-list.component");
const accounts_list_component_1 = require("./components/home.components/sales.component/accounts-list.component");
const finances_component_1 = require("./components/home.components/finances.component");
const new_marketing_campaign_component_1 = require("./components/home.components/marketing.component/new-marketing-campaign.component");
const marketing_service_1 = require("./services/marketing.service");
const campaign_list_component_1 = require("./components/home.components/marketing.component/campaign-list.component");
const impressum_component_1 = require("./components/impressum.component");
const accounts_sidebar_component_1 = require("./components/home.components/sales.component/accounts-sidebar.component");
const actual_component_1 = require("./components/home.components/finance.component/actual.component");
const new_research_component_1 = require("./components/home.components/research.component/new-research.component");
const new_kredit_component_1 = require("./components/home.components/finance.component/new-kredit.component");
const kredit_list_component_1 = require("./components/home.components/finance.component/kredit-list.component");
const research_service_1 = require("./services/research.service");
const research_list_component_1 = require("./components/home.components/research.component/research-list.component");
const old_research_list_component_1 = require("./components/home.components/research.component/old-research-list.component");
let HomeModule = class HomeModule {
};
HomeModule = __decorate([
    core_1.NgModule({
        imports: [
            platform_browser_1.BrowserModule,
            forms_1.FormsModule,
            forms_1.ReactiveFormsModule,
            http_1.HttpModule,
            angular2_chartjs_1.ChartModule,
            router_1.RouterModule.forRoot([
                {
                    path: 'dashboard',
                    component: dashboard_component_1.DashboardComponent
                },
                {
                    path: 'sales',
                    component: sales_component_1.SalesComponent
                },
                {
                    path: 'research',
                    component: research_component_1.ResearchComponent
                },
                {
                    path: 'produktion',
                    component: produktion_component_1.ProduktionComponent
                },
                {
                    path: 'marketing',
                    component: marketing_component_1.MarketingComponent
                },
                {
                    path: 'human-ressources',
                    component: human_ressources_component_1.HRComponent
                },
                {
                    path: 'accounts',
                    component: accounts_component_1.AccountsComponent
                },
                {
                    path: 'finances',
                    component: finances_component_1.FinancesComponent
                },
                {
                    path: 'home.html',
                    redirectTo: '/dashboard',
                    pathMatch: 'full'
                }
            ])
        ],
        declarations: [home_component_1.HomeComponent,
            header_component_1.HeaderComponent,
            dashboard_component_1.DashboardComponent,
            sales_component_1.SalesComponent,
            research_component_1.ResearchComponent,
            produktion_component_1.ProduktionComponent,
            marketing_component_1.MarketingComponent,
            human_ressources_component_1.HRComponent,
            accounts_component_1.AccountsComponent,
            keyfigures_component_1.KeyfiguresComponent,
            new_employee_component_1.NewEmployeeComponent,
            employee_list_component_1.EmployeeListComponent,
            soziale_leistungen_component_1.SozialeLeistungenComponent,
            new_product_line_component_1.NewProductLineComponent,
            new_machine_component_1.NewMachineComponent,
            new_warehouse_component_1.NewWarehouseComponent,
            new_production_hall_component_1.NewProductionHallComponent,
            productline_list_component_1.ProductLineComponent,
            machine_list_component_1.MachineListComponent,
            ausschreibungen_list_component_1.AusschreibungListComponent,
            opportunities_list_component_1.OpportunitiesListComponent,
            accounts_list_component_1.AccountsListComponent,
            finances_component_1.FinancesComponent,
            new_marketing_campaign_component_1.NewMarketingCampaignComponent,
            campaign_list_component_1.CampagneListComponent,
            impressum_component_1.ImpressumComponent,
            accounts_sidebar_component_1.AccountsSidebar,
            actual_component_1.ActualComponent,
            new_research_component_1.NewResearchComponent,
            new_kredit_component_1.NewKreditComponent,
            kredit_list_component_1.KreditListComponent,
            finances_component_1.FinancesComponent,
            research_list_component_1.ResearchListComponent,
            old_research_list_component_1.OldResearchListComponent
        ],
        bootstrap: [home_component_1.HomeComponent],
        providers: [hr_service_1.HRService, { provide: http_1.RequestOptions, useClass: auth_request_options_1.AuthRequestOptions },
            produktion_service_1.ProduktionService, keyfigures_service_1.KeyFiguresService, home_service_1.HomeService, research_service_1.ResearchService, sales_service_1.SalesService, marketing_service_1.MarketingService
        ]
    }),
    __metadata("design:paramtypes", [])
], HomeModule);
exports.HomeModule = HomeModule;
//# sourceMappingURL=home.module.js.map