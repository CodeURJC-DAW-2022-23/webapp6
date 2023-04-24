import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from '../models/book.model';
import { User } from '../models/user.model';
import { Review } from '../models/review.model';
import { Offer } from '../models/offer.model';
import { Page } from '../models/page.model';

const BASE_URL = '/api/admin/';

@Injectable({ providedIn: 'root' })
export class AdminService {

    constructor(private httpClient: HttpClient) { }

    getUsers(): Observable<User[]> {
        return this.httpClient.get(BASE_URL + "users/") as Observable<User[]>;
    }

    getUsersPaginated(n: number): Observable<Page> {
        return this.httpClient.get(BASE_URL + "users?page=" + n) as Observable<Page>;
    }

    addBook(book: Book): Observable<Book> {
        return this.httpClient.post(BASE_URL + "books/", book) as Observable<Book>;
    }

    updateUser(user: User) {
        return this.httpClient.put(BASE_URL + "users/" + user.id, user)
    }

    updateOffer(offer: Offer) {
        return this.httpClient.put(BASE_URL + "offers/" + offer.id, offer)
    }

    updateReview(review: Review) {
        return this.httpClient.put(BASE_URL + "reviews/" + review.id, review)
    }

    updateBook(book: Book) {
        return this.httpClient.put(BASE_URL + "books/" + book.id, book)
    }

    deleteUserImage(user: User) {
        return this.httpClient.delete(BASE_URL + "users/" + user.id + "/image")
    }

    deleteOfferImage(offer: Offer) {
        return this.httpClient.delete(BASE_URL + "offers/" + offer.id + "/image")
    }

    deleteBookImage(book: Book) {
        return this.httpClient.delete(BASE_URL + "books/" + book.id + "/image")
    }

    setUserImage(user: User, formData: FormData) {
        return this.httpClient.post(BASE_URL + "users/" + user.id + '/image', formData);
    }

    setOfferImage(offer: Offer, formData: FormData) {
        return this.httpClient.post(BASE_URL + "offers/" + offer.id + '/image', formData);
    }

    setBookImage(book: Book, formData: FormData) {
        return this.httpClient.post(BASE_URL + "books/" + book.id + '/image', formData);
    }

    deleteUser(user: User) {
        return this.httpClient.delete(BASE_URL + "users/" + user.id)
    }

    deleteOffer(offer: Offer) {
        return this.httpClient.delete(BASE_URL + "offers/" + offer.id)
    }

    deleteReview(review: Review) {
        return this.httpClient.delete(BASE_URL + "reviews/" + review.id)
    }

    deleteBook(book: Book) {
        return this.httpClient.delete(BASE_URL + "books/" + book.id)
    }

}
