import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';


const BASE_URL = '/api/statistics';

@Injectable({ providedIn: 'root' })
export class StatisticsService {

  constructor(private httpClient: HttpClient) { }


    getStatistics() {
      this.httpClient.get<Map<string, number>>('/api/statistics/');
      return
    }


    // Error handler
    private handleError(error: any) {
        console.log("ERROR:");
        console.error(error);
        return throwError("Server error (" + error.status + "): " + error.text())
    }

}
