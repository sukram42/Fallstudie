/**
 * Created by boebel on 10.01.2017.
 */

/**
 * Created by boebel on 04.01.2017.
 */

import {Component, Input, OnInit} from '@angular/core';

@Component({
    selector: 'kennzahl',
    templateUrl: '../../../../templates/components/home.components/dashboard.components/keyfigures.component.html'

})

export class KeyfiguresComponent {

    @Input('progress') progress: number;
    @Input('title') title: string;

}