import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';


const BASE_URL = '/api/statistics';

@Injectable({ providedIn: 'root' })
export class StatisticsService {



}
