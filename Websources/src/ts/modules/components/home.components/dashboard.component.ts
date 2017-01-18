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
        labels: ["Mitarbeiterzufriedenheit", "Kundenzufriedenheit", "Bekanntheitsgrad","Image","Produktqualität"],
        datasets: [
            {
                label: "Kennzahlen",
                backgroundColor: "rgba(255,173,41,0.7)",
                borderColor: "rgba(255,173,41,1)",
                data: [65, 59, 80, 81, 50]
            },
            {
                label: "kennzahlen des letzten Geschäftsjahres",
                backgroundColor: "rgba(49,53,61,0.7)",
                borderColor: "rgba(49,53,61,1)",
                data: [40, 60, 55, 31,35]
            }
        ]
    };
    options = {
        responsive: true,
        maintainAspectRatio: false,
        defaultFontSize: 100,
    };

    type2 = 'bar';
    data2 = {
        labels: ["Jan 2010", "Feb 2010", "Mär 2010","Apr 2010","Mai 2010","Jun 2010","Jul 2010","Aug 2010"],
        datasets: [
            {
                label: "Erträge",
                backgroundColor: "rgba(255,173,41,0.7)",
                borderColor: "rgba(255,173,41,1)",
                data: [100, 30, 20, 100,23 ,54, 60, 90]
            },
            {
                label: "Aufwendungen",
                backgroundColor: "rgba(49,53,61,0.7)",
                borderColor: "rgba(49,53,61,1)",
                data: [40, 60, 55, 31, 49, 30, 80, 48]
            }
        ]
    };
    options2 = {
        responsive: true,
        maintainAspectRatio: false,
        defaultFontSize: 100,
    };

    type3 = 'polarArea';
    data3 = {
        labels: ["Test AG", "Test AG 2", "Test AG 3"],
        datasets: [
            {
                label: "Erträge",
                backgroundColor: ["rgba(255,173,41,0.7)","rgba(49,35,61,0.7)","rgba(28,29,33,0.7)"],
                borderColor: ["rgba(255,173,41,1)","rgba(49,35,61,1)","rgba(28,29,33,1)"],
                data: [60, 30, 10]
            }
        ]
    };
    options3 = {
        responsive: true,
        maintainAspectRatio: false,
        defaultFontSize: 100,
    };
}