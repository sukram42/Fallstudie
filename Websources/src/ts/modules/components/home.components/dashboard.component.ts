/**
 * Created by boebel on 09.01.2017.
 */


import {Component, OnInit, ViewChild} from '@angular/core';
import {KeyFiguresService} from "../../services/keyfigures.service";
import {HomeService} from "../../services/home.service";

@Component({
    selector: 'home-component',
    templateUrl: '../../../../templates/components/home.components/dashboard.component.html',

})

export class DashboardComponent implements OnInit {

    @ViewChild('barchart') chart;
    @ViewChild('marktchart') marktanteile;

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
    marktanteileData = {
        labels: [],
        datasets: [
            {
                label: "Erträge",
                backgroundColor: ["rgba(255,173,41,0.7)", "rgba(49,35,61,0.7)", "rgba(28,29,33,0.7)"],
                borderColor: ["rgba(255,173,41,1)", "rgba(49,35,61,1)", "rgba(28,29,33,1)"],
                data: []
            }
        ]
    };
    options3 = {
        responsive: true,
        maintainAspectRatio: false,
        defaultFontSize: 100,
    };


    constructor(private _keyFigures: KeyFiguresService,private _homeService:HomeService) {
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
    fillMarktanteile()
    {
        this._homeService.getMarktanteile().subscribe(data=> {
            for (var key in data) {
                if (data.hasOwnProperty(key)) {
                    if (!this.aufwandchar.labels.includes(key)) {
                        this.marktanteileData.labels.push(key)
                        this.marktanteileData.datasets[0].data.push(data[key])
                    }
                }
            }
        });

        this.marktanteile.chart.update();
    }
}