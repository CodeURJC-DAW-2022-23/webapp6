import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/models/book.model';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'upload-offer',
  templateUrl: './upload-offer.component.html',
  styleUrls: ['./upload-offer.component.css']
})
export class UploadOfferComponent {

  book: Book | undefined;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public bookService: BookService) {

    const id = activatedRoute.snapshot.params['idBook'];

    this.bookService.getBook(id).subscribe(
      book => this.book = book,
      error => console.log(error)
    );
  }


  newOffer(book: Book) {
    // Call to services to create de offer
    this.router.navigate(['/books', book.id]);
  }

}
