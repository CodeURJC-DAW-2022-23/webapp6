import { OfferService } from 'src/app/services/offer.service';
import { Component, Input } from '@angular/core';
import { Offer } from 'src/app/models/offer.model';
import { Router } from '@angular/router';

@Component({
  selector: 'offer-card',
  templateUrl: './offer-card.component.html',
  styleUrls: ['./offer-card.component.css'],
})
export class OfferCard {
  @Input() offerId: string | undefined;

  offer: Offer | undefined;

  constructor(private router: Router, private offerService: OfferService) {}

  offerIdNumber(): number | undefined {
    return parseInt(this.offerId || '', 10);
  }

  ngOnInit() {
    this.offerService.getOffer(this.offerIdNumber()).subscribe(
      offer => this.offer = offer,
      error => console.log(error)
    );
  }

  getImage(offer: Offer) {
    return '/api/offers/' + offer.id + '/image';
  }

}
