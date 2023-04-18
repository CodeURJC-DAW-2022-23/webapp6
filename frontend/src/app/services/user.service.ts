import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { User } from '../models/user.model';
import { Page } from '../models/page.model';

const BASE_URL = '/api/user';

@Injectable({ providedIn: 'root' })
export class UserService {

  constructor(private httpClient: HttpClient) { }

}