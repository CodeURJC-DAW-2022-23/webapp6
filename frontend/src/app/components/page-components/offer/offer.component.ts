import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'offer',
  templateUrl: './offer.component.html',
  styleUrls: ['./offer.component.css']
})
export class OfferComponent {

  constructor(private router: Router) { }

  buyOffer(idBook: number, idOffer: number) {
    this.router.navigate(['/books', idBook, 'offers', idOffer, 'checkout']);
  }

  editOffer(idOffer: number) {
    this.router.navigate(['/offers', idOffer, 'update-offer']);
  }

}
