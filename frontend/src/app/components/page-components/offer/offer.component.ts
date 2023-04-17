import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Offer } from 'src/app/models/offer.model';
import { OfferService } from 'src/app/services/offer.service';

@Component({
  selector: 'offer',
  templateUrl: './offer.component.html',
  styleUrls: ['./offer.component.css']
})
export class OfferComponent {

  offer: Offer | undefined;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public offerService: OfferService) {

    const id = activatedRoute.snapshot.params['idOffer'];

    this.offerService.getOffer(id).subscribe(
      offer => this.offer = offer,
      error => console.log(error)
    );
  }

  getImage(offer: Offer) {
    return '/api/offers/' + offer.id + '/image';
  }

  buyOffer(offer: Offer) {
    this.router.navigate(['/offers', offer.id, 'checkout']);
  }

  editOffer(offer: Offer) {
    this.router.navigate(['/offers', offer.id, 'update-offer']);
  }

}
