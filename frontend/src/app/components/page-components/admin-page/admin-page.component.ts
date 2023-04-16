import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BookService } from 'src/app/services/book.service';
import { LoginService } from 'src/app/services/login.service';
import { Book } from 'src/app/models/book.model';
import { User } from 'src/app/models/user.model';
import { Offer } from 'src/app/models/offer.model';
import { Review } from 'src/app/models/review.model';
import { UserService } from 'src/app/services/user.service';
import { OfferService } from 'src/app/services/offer.service';
import { ReviewService } from 'src/app/services/review.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent {

  users: User[] | undefined;
  offers: Offer[] | undefined;
  reviews: Review[] | undefined;
  books: Book[] | undefined;

  constructor(private router: Router, 
    private userService: UserService, private offerService: OfferService, private reviewService: ReviewService, private bookService: BookService, 
    public loginService: LoginService) { }

  ngOnInit() {

    this.userService.getUsers().subscribe(
      users => this.users = users,
      error => console.log(error)
    );

    this.offerService.getOffers().subscribe(
      offers => this.offers = offers,
      error => console.log(error)
    );

    this.reviewService.getReviews().subscribe(
      reviews => this.reviews = reviews,
      error => console.log(error)
    );

    this.bookService.getBooks().subscribe(
      books => this.books = books,
      error => console.log(error)
    );

  }

  userImage(user: User) {
    return '/api/admin/users/' + user.id + '/image';
  }

  offerImage(offer: Offer) {
    return '/api/offers/' + offer.id + '/image';
  }

  bookImage(book: Book) {
    return '/api/books/' + book.id + '/image';
  }

}
