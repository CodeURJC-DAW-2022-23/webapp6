import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';

import { Book } from '../models/book.model';
import { Review } from '../models/review.model';
import { Offer } from '../models/offer.model';
import { Page } from '../models/page.model';

const BASE_URL = '/api/books';

@Injectable({ providedIn: 'root' })
export class BookService {
  constructor(private httpClient: HttpClient) { }

  getBooks(): Observable<Book[]> {
    return this.httpClient.get(BASE_URL + '/') as Observable<Book[]>;
  }

  getBooksPaginated(n: number): Observable<Page> {
    return this.httpClient.get(BASE_URL + "?page=" + n) as Observable<Page>;
  }

  getBook(id: number | undefined): Observable<Book> {
    return this.httpClient.get(BASE_URL + '/' + id) as Observable<Book>;
  }

  getOffersPaginated(id: number, n: number): Observable<Page> {
    return this.httpClient.get(BASE_URL + "/" + id + "/offers" + "?page=" + n) as Observable<Page>;
  }

  getReviewsPaginated(id: number, n: number): Observable<Page> {
    return this.httpClient.get(BASE_URL + "/" + id + "/reviews" + "?page=" + n) as Observable<Page>;
  }

  getImage(id: number) {
    return BASE_URL + '/' + id + '/image';
  }

  getReviews(id: number): Observable<Review[]> {
    return this.httpClient.get(BASE_URL + "/" + id + "/reviews/all") as Observable<Review[]>;
  }

  getOffers(id: number): Observable<Offer[]> {
    return this.httpClient.get(BASE_URL + "/" + id + "/offers/all") as Observable<Offer[]>;
  }

  addFavorite(id: number){
    return this.httpClient.post("/api/user/favorites/" + id, {});
  }

  deleteFavorite(id: number){
    return this.httpClient.delete("/api/user/favorites/" + id, {});
  }

  getFavorites(): Observable<Book[]> {
    return this.httpClient.get("/api/user/favorites/") as Observable<Book[]>;
  }

  getRecommendedBooks(): Observable<Book[]> {
    return this.httpClient.get("/api/books/algorithm") as Observable<Book[]>;
  }

  getBestPick(): Observable<Book> {
    return this.httpClient.get("/api/books/bestpick") as Observable<Book>;
  }

}
