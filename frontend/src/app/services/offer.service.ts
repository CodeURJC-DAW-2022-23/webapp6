import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Offer } from '../models/offer.model';
import { Page } from '../models/page.model';
import { OfferTDO } from '../models/offerTDO.model';

const BASE_URL = '/api/offers';

@Injectable({ providedIn: 'root' })
export class OfferService {

	constructor(private httpClient: HttpClient) { }

	getOffers(): Observable<Offer[]> {
		return this.httpClient.get(BASE_URL + "/") as Observable<Offer[]>
	}

	getOffersPaginated(n: number): Observable<Page> {
		return this.httpClient.get(BASE_URL + "?page=" + n) as Observable<Page>
	}

	getOffer(id: number | undefined): Observable<Offer> {
		return this.httpClient.get(BASE_URL + "/" + id) as Observable<Offer>
	}

	getImage(id: number) {
		return BASE_URL + "/" + id + '/image';
	}

	addOffer(id: number, offer: OfferTDO): Observable<Offer> {
		return this.httpClient.post("/api/books/" + id + "/offers", offer) as Observable<Offer>;
	}

	setOfferImage(offer: Offer, formData: FormData) {
		return this.httpClient.post(BASE_URL + "/" + offer.id + '/image', formData);
	}

	buyOffer(offer: Offer) {
		return this.httpClient.put(BASE_URL + "/" + offer.id + "/sold", {});
	}

  updateOffer(offer: Offer) {
    return this.httpClient.put(BASE_URL + "/" + offer.id, offer)
  }

  deleteOfferImage(offer: Offer) {
    return this.httpClient.delete(BASE_URL + "/" + offer.id + "/image")
}


}
