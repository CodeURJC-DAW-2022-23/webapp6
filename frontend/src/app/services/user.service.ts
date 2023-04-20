import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { User } from '../models/user.model';

import { Book } from '../models/book.model';
import { Review } from '../models/review.model';
import { Offer } from '../models/offer.model';
import { Page } from '../models/page.model';

const BASE_URL = '/api/user';

@Injectable({ providedIn: 'root' })
export class UserService {
  constructor(private httpClient: HttpClient) { }

  getUser(): Observable<User> {
    return this.httpClient.get<User>(BASE_URL + '/');
}


  getUserBooksPaginated(n: number): Observable<Page> {
    return this.httpClient.get(BASE_URL + "?page=" + n) as Observable<Page>;
  }

  getUserFavoritesPaginated(n: number): Observable<Page> {
    return this.httpClient.get(BASE_URL + '/favorites' + "?page=" + n) as Observable<Page>;
  }

  getBook(id: number | undefined): Observable<Book> {
    return this.httpClient.get(BASE_URL + '/' + id) as Observable<Book>;
  }

  getUserOffersPaginated(n: number): Observable<Page> {
    return this.httpClient.get(BASE_URL + "/offers" + "?page=" + n) as Observable<Page>;
  }

  getUserReviewsPaginated(n: number): Observable<Page> {
    return this.httpClient.get(BASE_URL + "/reviews" + "?page=" + n) as Observable<Page>;
  }

  getUserImage(): Observable<any> {
    return this.httpClient.get(BASE_URL + '/image', { responseType: 'blob' });
  }

  getUserReviews(): Observable<Review[]> {
    return this.httpClient.get(BASE_URL + "/reviews/all") as Observable<Review[]>;
  }

  getUserOffers(): Observable<Page> {
    return this.httpClient.get<Page>(BASE_URL + '/offers/');
  }

 /*  getUserHistorial(): Observable<Offer[]> {
    return this.httpClient.get(BASE_URL + "/historial") as Observable<Offer[]>;
  } */

  getUserHistorialPaginated(n: number): Observable<Page> {
    return this.httpClient.get(BASE_URL + "/historial" + "?page=" + n) as Observable<Page>;
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

}

