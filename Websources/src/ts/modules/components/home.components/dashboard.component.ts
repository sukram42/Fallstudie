/**
 * Created by boebel on 09.01.2017.
 */

/**
 * Created by boebel on 04.01.2017.
 */

import {Component, OnInit} from '@angular/core';
import {KeyFiguresService} from "../../services/keyfigures.service";

@Component({
    selector: 'home-component',
    templateUrl: '../../../../templates/components/home.components/dashboard.component.html',

})

export class DashboardComponent implements OnInit
{

    keyfigures;
    type = 'radar';
    data;
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



    constructor(private keyFigures : KeyFiguresService)
    {
        keyFigures.getKeyFigures()
            .subscribe(content=>{
                if(!this.data) {
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
                }else
                {
                    this.data.datasets[0].data[0] =content.mitarbeiterzufriedenheit ;
                    this.data.datasets[0].data[1] =content.kundenzufriedenheit;
                    this.data.datasets[0].data[2] =content.bekanntheitsgrad;
                    this.data.datasets[0].data[3] =content.image ;
                    this.data.datasets[0].data[4] =content.marktanteil ;
                }

          });
    }
    ngOnInit(): void {
    }
}