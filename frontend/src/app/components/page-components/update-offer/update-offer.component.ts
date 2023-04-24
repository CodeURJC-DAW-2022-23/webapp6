import { Component, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Offer } from 'src/app/models/offer.model';
import { OfferService } from 'src/app/services/offer.service';

@Component({
  selector: 'update-offer',
  templateUrl: './update-offer.component.html',
  styleUrls: ['./update-offer.component.css']
})
export class UpdateOfferComponent {

  offer: Offer | undefined;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public offerService: OfferService) {

    const id = activatedRoute.snapshot.params['idOffer'];

    this.offerService.getOffer(id).subscribe(
      offer => this.offer = offer,
      error => console.log(error)
    );
  }

  updateOffer(offer: Offer) {
    this.offerService.updateOffer(offer).subscribe(
      _ => {
        this.offerService.deleteOfferImage(offer);
        this.uploadImage(offer);
        this.router.navigate(['/offers', offer.id]);
      },
      error => console.error(error)
    );
  }

  @ViewChild("file")
  file: any;
  uploadImage(offer: Offer): void {
    const image = this.file.nativeElement.files[0];
    if (image) {
      let formData = new FormData();
      formData.append("imageFile", image);
      this.offerService.setOfferImage(offer, formData).subscribe(
        response => {
          console.log(response);
        },
        error => console.log('Error uploading offer image: ' + error)
      );
    }
  }

  getImage(offer: Offer) {
    if (offer.id != undefined) {
      return this.offerService.getImage(offer.id);
    }
    else {
      return "";
    }
  }

}
