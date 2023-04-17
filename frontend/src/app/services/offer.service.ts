import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Offer } from '../models/offer.model';

const BASE_URL = '/api/offers';

@Injectable({ providedIn: 'root' })
export class OfferService {

  constructor(private httpClient: HttpClient) { }

  getOffers(): Observable<Offer[]> {
	return this.httpClient.get(BASE_URL + "/") as Observable<Offer[]>
}

  getOffersByBookPaginated(id: number, n: number) {
		return this.httpClient.get("/api/books/" + id + "/offers" + "?page=" + n).pipe(
			catchError(catchError((error) => this.handleError(error)))
		);
	}

  getOffer(id: number | undefined): Observable <Offer> {
    return this.httpClient.get(BASE_URL + "/" + id) as Observable<Offer>
  }

  getImage(id: number){
    return BASE_URL + "/" + id + '/image';
  }

  addOffer(offer: Offer, id: number) {

		if (!offer.id) {
			return this.httpClient.post("/api/books/" + id + "/offers", offer)
				.pipe(
					catchError(error => this.handleError(error))
				);
		} else {
			return this.httpClient.put(BASE_URL + "/" + offer.id, offer).pipe(
				catchError(error => this.handleError(error))
			);
		}
	}

  setOfferImage(offer: Offer, formData: FormData) {
		return this.httpClient.post(BASE_URL + "/" + offer.id + '/image', formData)
			.pipe(
				catchError(error => this.handleError(error))
			);
	}

	deleteOfferImage(offer: Offer) {
		return this.httpClient.delete(BASE_URL + "/" + offer.id + '/image')
			.pipe(
				catchError(error => this.handleError(error))
			);
	}

	deleteOffer(offer: Offer) {
		return this.httpClient.delete(BASE_URL + "/" + offer.id).pipe(
			catchError(error => this.handleError(error))
		);
	}

	updateOffer(offer: Offer) {
		return this.httpClient.put(BASE_URL + "/" + offer.id, offer).pipe(
			catchError(error => this.handleError(error))
		);
	}

  buyOffer(offer: Offer) {
    return this.httpClient.put(BASE_URL + "/" + offer.id +"/sold", {} ).pipe(
			catchError(error => this.handleError(error))
		);
  }

	private handleError(error: any) {
		console.log("ERROR:");
		console.error(error);
		return throwError("Server error (" + error.status + "): " + error.text())
	}


}
