import { Component, ViewChild } from '@angular/core';
import { Offer } from 'src/app/models/offer.model';
import { OfferService } from 'src/app/services/offer.service';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'offers-table',
  templateUrl: './offers-table.component.html',
  styleUrls: ['../admin-page.component.css']
})
export class OffersTableComponent {

  offers: Offer[];
  loadedPage = 0;
  loadMoreButton = true;

  @ViewChild("file")
  file: any;

  constructor(private adminService: AdminService, private offerService: OfferService) {
    this.offers = [];
  }

  ngOnInit() {
    this.loadOffers(this.loadedPage);
  }

  nextPage() {
    this.loadedPage += 1;
    this.loadOffers(this.loadedPage)
  }

  loadOffers(page: number) {
    this.offerService.getOffersPaginated(page).subscribe(
      loadedOffers => {
        this.offers = this.offers.concat(loadedOffers.content);
        this.loadMoreButton = loadedOffers.numberOfElements != 0;
      },
      error => console.log(error)
    );
  }

  reload() {
    for (var i = 0; i <= this.loadedPage; i++) {
      this.offers = [];
      this.loadOffers(i);
    }
  }

  updateOffer(offer: Offer) {
    this.adminService.updateOffer(offer).subscribe(
      _ => {
        this.adminService.deleteOfferImage(offer);
        this.uploadOfferImage(offer);
      },
      error => console.error(error)
    );
  }

  deleteOffer(offer: Offer) {
    this.adminService.deleteOffer(offer).subscribe(
      _ => this.offers.splice(this.offers.indexOf(offer), 1),
      error => console.error(error)
    );
  }

  uploadOfferImage(offer: Offer): void {
    const image = this.file.nativeElement.files[0];
    if (image) {
      let formData = new FormData();
      formData.append("imageFile", image);
      this.adminService.setOfferImage(offer, formData).subscribe(
        response => {
          console.log(response);
          this.reload();
        },
        error => alert('Error uploading book image: ' + error)
      );
    }
  }

  offerImage(offer: Offer) {
    if (offer.id) {
      return this.offerService.getImage(offer.id); 
    }
    else return
  }
}
