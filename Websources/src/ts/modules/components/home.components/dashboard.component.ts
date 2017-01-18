/**
 * Created by boebel on 09.01.2017.
 */

/**
 * Created by boebel on 04.01.2017.
 */

import {Component} from '@angular/core';

@Component({
    selector: 'home-component',
    templateUrl: '../../../../templates/components/home.components/dashboard.component.html',

})

export class DashboardComponent {
    type = 'radar';
    data = {
        labels: ["Mitarbeiterzufriedenheit", "Kundenzufriedenheit", "Bekanntheitsgrad","Image"],
        datasets: [
            {
                label: "Kennzahlen",
                data: [65, 59, 80, 81]
            }
        ]
    };
    options = {
        responsive: true,
        maintainAspectRatio: false
    };
}