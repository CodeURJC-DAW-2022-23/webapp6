import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Review } from '../models/review.model';

const BASE_URL = '/api/reviews';

@Injectable({ providedIn: 'root' })
export class ReviewService {

  constructor(private httpClient: HttpClient) { }


	getReviewsByBookPaginated(id: number, n: number) {
		return this.httpClient.get("/api/books/" + id + "/reviews" + "?page=" + n).pipe(
			catchError(catchError((error) => this.handleError(error)))
		);
	}

  addReview(review: Review, id: number) {

		if (!review.id) {
			return this.httpClient.post("/api/books/" + id + "/reviews", review)
				.pipe(
					catchError(error => this.handleError(error))
				);
		} else {
			return this.httpClient.put(BASE_URL + "/" + review.id, review).pipe(
				catchError(error => this.handleError(error))
			);
		}
	}

	deleteReview(review: Review) {
		return this.httpClient.delete(BASE_URL + "/" + review.id).pipe(
			catchError(error => this.handleError(error))
		);
	}

	updateReview(review: Review) {
		return this.httpClient.put(BASE_URL + "/" + review.id, review).pipe(
			catchError(error => this.handleError(error))
		);
	}

	private handleError(error: any) {
		console.log("ERROR:");
		console.error(error);
		return throwError("Server error (" + error.status + "): " + error.text())
	}


}
