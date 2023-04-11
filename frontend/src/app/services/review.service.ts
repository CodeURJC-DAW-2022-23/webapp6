import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Review } from '../models/review.model';

const BASE_URL = '/api/reviews';

@Injectable({ providedIn: 'root' })
export class ReviewService {




}
