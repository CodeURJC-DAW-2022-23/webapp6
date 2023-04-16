import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'upload-offer',
  templateUrl: './upload-offer.component.html',
  styleUrls: ['./upload-offer.component.css']
})
export class UploadOfferComponent {

  constructor(private router: Router) { }

  newOffer(id: number) {
    // Call to services to create de offer
    this.router.navigate(['/books', id]);
  }

}
