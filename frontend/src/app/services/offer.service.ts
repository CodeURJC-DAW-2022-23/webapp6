import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Offer } from '../models/offer.model';

const BASE_URL = '/api/offers';

@Injectable({ providedIn: 'root' })
export class OfferService {




}
