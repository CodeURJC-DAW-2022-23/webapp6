import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';

import { Book } from '../models/book.model';
import { User } from '../models/user.model';

const BASE_URL = '/api/user';

@Injectable({ providedIn: 'root' })
export class UserService {

    constructor(private httpClient: HttpClient) { }

    getUsers(): Observable<User[]> {
		return this.httpClient.get("/api/admin/users/") as Observable<User[]>;
	}

}
