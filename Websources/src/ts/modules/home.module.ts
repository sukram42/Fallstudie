
/**
 * Created by boebel on 04.01.2017.
 */


import {NgModule}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {HttpModule, RequestOptions} from '@angular/http';

import {HomeComponent} from "./components/home.component";
import {HeaderComponent} from "./components/header.component";
import {AuthRequestOptions} from "./services/auth-request-options";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule
    ],
    declarations: [HomeComponent,HeaderComponent],
    bootstrap:    [HomeComponent],
    providers : [
        {provide: RequestOptions, useClass : AuthRequestOptions}
    ]
})
export class HomeModule {
}
