import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/models/book.model';
import { Offer } from 'src/app/models/offer.model';
import { Review } from 'src/app/models/review.model';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'book-particular',
  templateUrl: './book-particular.component.html',
  styleUrls: ['./book-particular.component.css']
})
export class BookParticularComponent {

  book: Book | undefined;
  reviews: Review[] | undefined;
  offers: Offer[] | undefined;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public bookService: BookService) {

    const id = activatedRoute.snapshot.params['idBook'];

    this.bookService.getBook(id).subscribe(
      book => this.book = book,
      error => console.log(error)
    );

    this.bookService.getReviews(id).subscribe(
      reviews => this.reviews = reviews,
      error => console.log(error)
    );

    this.bookService.getOffers(id).subscribe(
      offers => this.offers = offers,
      error => console.log(error)
    );

  }

  getImage(book: Book) {
    return '/api/books/' + book.id + '/image';
  }

  uploadOffer(book: Book) {
    this.router.navigate(['/books', book.id, 'upload-offer']);
  }

  uploadReview(book: Book) {
    // Call to services to create de offer
    this.router.navigate(['/books', book.id, 'upload-review']);
  }
}
