import { Review } from './../models/review.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Page } from '../models/page.model';
import { ReviewTDO } from '../models/reviewTDO.model';

const BASE_URL = '/api/reviews';

@Injectable({ providedIn: 'root' })
export class ReviewService {

	constructor(private httpClient: HttpClient) { }

	getReviews(): Observable<Review[]> {
		return this.httpClient.get(BASE_URL + "/") as Observable<Review[]>
	}

	getReviewsPaginated(n: number): Observable<Page> {
		return this.httpClient.get(BASE_URL + "?page=" + n) as Observable<Page>
	}

	getReview(id: number | undefined): Observable<Review> {
		return this.httpClient.get(BASE_URL + "/" + id) as Observable<Review>
	}

	addReview(id:number, review: ReviewTDO): Observable<Review> {
    return this.httpClient.post("/api/books/" + id + "/reviews", review) as Observable<Review>;
  }

}
