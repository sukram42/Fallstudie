import {NgModule}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import {StartComponent} from "./components/start.component";
import {LoginFormComponent} from "./components/login-formular.component";
import {RegisterFormularComponent} from "./components/register-formular.component";
import {PlayerComponent} from "./components/player.component";
import {FAQComponent} from "./components/faq.component";
import {ImpressumComponent} from "./components/impressum.component";
import {DatenschutzComponent} from "./components/datenschutz.component";
import {MapToIterable} from "./pipes/map.pipe";
import {HighscoreComponent} from "./components/highscore.component";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule
    ],
    declarations: [
        StartComponent,
        LoginFormComponent,
        RegisterFormularComponent,
        PlayerComponent,
        FAQComponent,
        ImpressumComponent,
        DatenschutzComponent,
        MapToIterable,
        HighscoreComponent],

    bootstrap:    [StartComponent]
})
export class AppModule {
}

