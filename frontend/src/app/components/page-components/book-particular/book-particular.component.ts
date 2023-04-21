import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/models/book.model';
import { Offer } from 'src/app/models/offer.model';
import { Page } from 'src/app/models/page.model';
import { Review } from 'src/app/models/review.model';
import { BookService } from 'src/app/services/book.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'book-particular',
  templateUrl: './book-particular.component.html',
  styleUrls: ['./book-particular.component.css']
})
export class BookParticularComponent {

  book: Book | undefined;
  reviews: Page | undefined;
  offers: Page | undefined;
  favorites: Book[] | undefined;
  pageOffers: number = 0;
  pageReviews: number = 0;
  showButtonOffers: boolean = true;
  showButtonReviews: boolean = true;
  logged: boolean | undefined;
  isFavorite: boolean | undefined;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public bookService: BookService, public loginService: LoginService) {

    const id = activatedRoute.snapshot.params['idBook'];

    this.bookService.getBook(id).subscribe(
      book => this.book = book,
      error => console.log(error)
    );

    if (this.loginService.isLogged()){
      this.bookService.getFavorites().subscribe(
        favorites => {
          this.favorites = favorites;
          if (this.book != undefined && this.favorites.some((favorite) => favorite.id === this.book?.id)) {
            this.isFavorite = true;
          } else {
            this.isFavorite = false;
          }
        },
        error => console.log(error)
      );
    }

    this.bookService.getReviewsPaginated(id,0).subscribe(
      reviews => this.reviews = reviews,
      error => console.log(error)
    );

    this.bookService.getOffersPaginated(id, 0).subscribe(
      offers => this.offers = offers,
      error => console.log(error)
    );
  }




  loadMoreOffers(book: Book, n: number) {
    this.pageOffers = n;
    if (book.id !== undefined) {
      this.bookService.getOffersPaginated(book.id, n).subscribe(
        newOffers => {
          if (this.offers != undefined && newOffers.numberOfElements != 0){
            this.offers.content = this.offers.content.concat(newOffers.content);
          }else{
            this.showButtonOffers = false;
          }
        },
        error => console.log(error)
      );
    }
  }

  loadMoreReviews(book: Book, n: number) {
    this.pageReviews = n;
    if (book.id !== undefined) {
      this.bookService.getReviewsPaginated(book.id, n).subscribe(
        newReviews => {
          if (this.reviews != undefined && newReviews.numberOfElements != 0){
            this.reviews.content = this.reviews.content.concat(newReviews.content);
          }else{
            this.showButtonReviews = false;
          }
        },
        error => console.log(error)
      );
    }
  }

  getImage(book: Book) {
    if (book.id != undefined){
      return this.bookService.getImage(book.id);
    }
    else{
      return "";
    }
  }

  uploadOffer(book: Book) {
    this.router.navigate(['/books', book.id, 'upload-offer']);
  }

  uploadReview(book: Book) {
    this.router.navigate(['/books', book.id, 'upload-review']);
  }

  goLogin(){
    this.router.navigate(['/login'])
  }

  addFavorite(book: Book) {
    if (book.id != undefined){
    this.bookService.addFavorite(book.id).subscribe(
      response => {
        console.log(response);
        this.isFavorite = true;
      },
      error => console.log(error)
    );
    }
  }

  deleteFavorite(book: Book) {
    if (book.id != undefined){
    this.bookService.deleteFavorite(book.id).subscribe(
      response => {
        console.log(response);
        this.isFavorite = false;
      },
      error => console.log(error)
    );
    }
  }

}
