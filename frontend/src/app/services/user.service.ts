import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Book } from '../models/book.model';
import { User } from '../models/user.model';
import { Review } from '../models/review.model';
import { Offer } from '../models/offer.model';

const BASE_URL = '/api/user';

@Injectable({ providedIn: 'root' })
export class UserService {



}
