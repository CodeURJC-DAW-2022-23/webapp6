import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Book } from '../models/book.model';

const BASE_URL = '/api/books';

@Injectable({ providedIn: 'root' })
export class BookService {

    constructor(private httpClient: HttpClient) { }

    getBooks(): Observable<Book[]> {
		return this.httpClient.get(BASE_URL + "/") as Observable<Book[]>;
	}

}
