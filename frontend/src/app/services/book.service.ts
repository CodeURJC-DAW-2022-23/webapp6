import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Book } from '../models/book.model';
import { Review } from '../models/review.model';
import { Offer } from '../models/offer.model';
import { Page } from '../models/page.model';

const BASE_URL = '/api/books';

@Injectable({ providedIn: 'root' })
export class BookService {
  constructor(private httpClient: HttpClient) {}

  getBooks(): Observable<Book[]> {
    return this.httpClient.get(BASE_URL + '/') as Observable<Book[]>;
  }

  getBook(id: number | undefined): Observable<Book> {
    return this.httpClient.get(BASE_URL + '/' + id) as Observable<Book>;
  }

  getOffersPaginated(id: number, n: number): Observable<Page>{
		return this.httpClient.get(BASE_URL +"/" + id + "/offers" + "?page=" + n) as Observable<Page> ;
	}

  getReviewsPaginated(id: number, n: number): Observable<Page>{
		return this.httpClient.get(BASE_URL +"/" + id + "/reviews" + "?page=" + n) as Observable<Page> ;
	}


  getImage(id: number) {
    return BASE_URL + '/' + id + '/image';
  }

  getReviews(id: number): Observable<Review[]> {
		return this.httpClient.get(BASE_URL + "/" + id + "/reviews/all")as Observable<Review[]>;
	}

  getOffers(id: number): Observable<Offer[]> {
		return this.httpClient.get(BASE_URL + "/" + id + "/offers/all")as Observable<Offer[]>;
	}

}
