import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class StatisticsService {

  constructor(private httpClient: HttpClient) { }
    getStatistics() {
      this.httpClient.get<Map<string, number>>('/api/statistics/');
      return
    }

}
