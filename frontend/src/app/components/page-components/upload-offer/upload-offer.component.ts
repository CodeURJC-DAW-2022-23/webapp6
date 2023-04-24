import { Component, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/models/book.model';
import { Offer } from 'src/app/models/offer.model';
import { OfferTDO } from 'src/app/models/offerTDO.model';
import { BookService } from 'src/app/services/book.service';
import { OfferService } from 'src/app/services/offer.service';

@Component({
  selector: 'upload-offer',
  templateUrl: './upload-offer.component.html',
  styleUrls: ['./upload-offer.component.css']
})
export class UploadOfferComponent {

  book: Book | undefined;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public bookService: BookService, public offerService: OfferService) {

    const id = activatedRoute.snapshot.params['idBook'];

    this.bookService.getBook(id).subscribe(
      book => this.book = book,
      error => console.log(error)
    );
  }

  newOffer: OfferTDO = { edition: '', description: '', price: 0, image: false };

  addNewOffer(book: Book) {
    if (book.id != undefined) {
      this.offerService.addOffer(book.id, this.newOffer).subscribe(
        response => {
          console.log(response);
          this.uploadImage(response);
          console.log("La oferta se ha subido correctamente");
          this.router.navigate(['/books', book.id]);
        },
        error => console.log(error)
      );
    }
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

}
