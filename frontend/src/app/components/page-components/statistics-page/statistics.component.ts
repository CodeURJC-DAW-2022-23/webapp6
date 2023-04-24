import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface Statistics {
  [key: string]: number;
}

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css'],
})


export class StatisticsComponent implements OnInit {
  statistics: Statistics | undefined;

  public barChartOptions = {
    scaleShowVerticalLines: false,
    responsive: true,
  };
  public barChartLabels = ['Store Statistics'];
  public barChartType = 'bar';
  public barChartLegend = true;

  barChartData: { label: string, data: number[] }[] = [
    { label: 'Number of users', data: [] },
    { label: 'Number of books', data: [] },
    { label: 'Number of offers', data: [] },
    { label: 'Number of reviews', data: [] }
  ];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<Statistics>('/api/statistics/')
      .subscribe((data) => {

        this.statistics = data;
        this.barChartData[0].data.push(this.statistics['Number of users']);
        this.barChartData[1].data.push(this.statistics['Number of books']);
        this.barChartData[2].data.push(this.statistics['Number of offers']);
        this.barChartData[3].data.push(this.statistics['Number of reviews']);
      },
        (error) => console.log("Credenciales incorrectas"));
  }

}
