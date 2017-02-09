/**
 * Created by boebel on 09.01.2017.
 */


import {Component, OnInit, ViewChild} from '@angular/core';
import {KeyFiguresService} from "../../services/keyfigures.service";

@Component({
    selector: 'home-component',
    templateUrl: '../../../../templates/components/home.components/dashboard.component.html',

})

export class DashboardComponent implements OnInit {

    @ViewChild('barchart') chart;

    keyfigures;
    type = 'radar';
    data;
    options = {
        responsive: true,
        maintainAspectRatio: false,
        defaultFontSize: 100,
    };


    type2 = 'bar';
    aufwandchar = {
        labels: [],
        datasets: [
            {
                label: "Erträge",
                backgroundColor: "rgba(255,173,41,0.7)",
                borderColor: "rgba(255,173,41,1)",
                data: []
            },
            {
                label: "Aufwendungen",
                backgroundColor: "rgba(49,53,61,0.7)",
                borderColor: "rgba(49,53,61,1)",
                data: []
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
                backgroundColor: ["rgba(255,173,41,0.7)", "rgba(49,35,61,0.7)", "rgba(28,29,33,0.7)"],
                borderColor: ["rgba(255,173,41,1)", "rgba(49,35,61,1)", "rgba(28,29,33,1)"],
                data: [60, 30, 10]
            }
        ]
    };
    options3 = {
        responsive: true,
        maintainAspectRatio: false,
        defaultFontSize: 100,
    };


    constructor(private _keyFigures: KeyFiguresService) {
        _keyFigures.getArchivKennzahlen()
            .subscribe(data => this.fillAufwandChar(data));

        _keyFigures.getSoftKeyFigures()
            .subscribe(content => {
                if (!this.data) {
                    this.data = {
                        labels: ["Mitarbeiterzufriedenheit", "Kundenzufriedenheit", "Bekanntheitsgrad", "Image", "Marktanteil"],
                        datasets: [
                            {
                                label: "Kennzahlen",
                                backgroundColor: "rgba(255,173,41,0.7)",
                                borderColor: "rgba(255,173,41,1)",
                                data: [content.mitarbeiterzufriedenheit, content.kundenzufriedenheit, content.bekanntheitsgrad, content.image, 0]
                            }//,
                            // {
                            //     label: "kennzahlen des letzten Geschäftsjahres",
                            //     backgroundColor: "rgba(49,53,61,0.7)",
                            //     borderColor: "rgba(49,53,61,1)",
                            //     data: [0, 0, 0, 0,0]
                            // }
                        ]
                    };
                } else {
                    this.data.datasets[0].data[0] = content.mitarbeiterzufriedenheit;
                    this.data.datasets[0].data[1] = content.kundenzufriedenheit;
                    this.data.datasets[0].data[2] = content.bekanntheitsgrad;
                    this.data.datasets[0].data[3] = content.image;
                    this.data.datasets[0].data[4] = content.marktanteil;
                }
            });
    }

    ngOnInit(): void {
    }

    fillAufwandChar(data: {}) {
        for (var key in data) {
            if (data.hasOwnProperty(key)) {
                var guv = data[key];

                if (!this.aufwandchar.labels.includes(guv.key)) {
                    this.aufwandchar.labels.push(guv.key);
                    this.aufwandchar.datasets[0].data.push(guv.value.erloeseArchiv);
                    this.aufwandchar.datasets[1].data.push(guv.value.aufwendungenArchiv);
                }
            }
        }
        this.chart.chart.update();
    }
}