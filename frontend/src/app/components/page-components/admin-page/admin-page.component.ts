import { Component, ViewChild } from '@angular/core';
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
import { Page } from 'src/app/models/page.model';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent {

  users: Page | undefined;
  usersPage = 0;
  usersButton = true;

  offers: Page | undefined;
  offersPage = 0;
  offersButton = true;

  reviews: Page | undefined;
  reviewsPage = 0;
  reviewsButton = true;

  books: Page | undefined;
  booksPage = 0;
  booksButton = true;

  constructor(private userService: UserService, private offerService: OfferService,
    private reviewService: ReviewService, private bookService: BookService,
    public loginService: LoginService) { }

  ngOnInit() {

    // Force admin login while real login functionalitie is not implemented:
    this.loginService.logIn('admin', 'adminpass')

    this.userService.getUsersPaginated(0).subscribe(
      users => this.users = users,
      error => console.log(error)
    );

    this.offerService.getOffersPaginated(0).subscribe(
      offers => this.offers = offers,
      error => console.log(error)
    );

    this.reviewService.getReviewsPaginated(0).subscribe(
      reviews => this.reviews = reviews,
      error => console.log(error)
    );

    this.loadBooks();

  }

  loadBooks() {
    this.bookService.getBooksPaginated(0).subscribe(
      books => this.books = books,
      error => console.log(error)
    );
  }

  loadMoreUsers(n: number) {
    this.usersPage = n;
    this.userService.getUsersPaginated(n).subscribe(
      newUsers => {
        if (this.users != undefined && newUsers.numberOfElements != 0) {
          this.users.content = this.users.content.concat(newUsers.content);
        } else {
          this.usersButton = false;
        }
      },
      error => console.log(error)
    );
  }

  loadMoreOffers(n: number) {
    this.offersPage = n;
    this.offerService.getOffersPaginated(n).subscribe(
      newOffers => {
        if (this.offers != undefined && newOffers.numberOfElements != 0) {
          this.offers.content = this.offers.content.concat(newOffers.content);
        } else {
          this.offersButton = false;
        }
      },
      error => console.log(error)
    );
  }

  loadMoreReviews(n: number) {
    this.reviewsPage = n;
    this.reviewService.getReviewsPaginated(n).subscribe(
      newReviews => {
        if (this.reviews != undefined && newReviews.numberOfElements != 0) {
          this.reviews.content = this.reviews.content.concat(newReviews.content);
        } else {
          this.reviewsButton = false;
        }
      },
      error => console.log(error)
    );
  }

  loadMoreBooks(n: number) {
    this.booksPage = n;
    this.bookService.getBooksPaginated(n).subscribe(
      newBooks => {
        if (this.books != undefined && newBooks.numberOfElements != 0) {
          this.books.content = this.books.content.concat(newBooks.content);
        } else {
          this.booksButton = false;
        }
      },
      error => console.log(error)
    );
  }

  newBook: Book = { title: '', author: '', genre: '', image: false };

  addNewBook() {
    this.bookService.addBook(this.newBook).subscribe(
      response => {
        console.log(response);
        this.uploadImage(response);
        this.loadBooks();
      },
      error => console.log(error)
    );
  }

  @ViewChild("file")
  file: any;
  uploadImage(book: Book): void {
    const image = this.file.nativeElement.files[0];
    if (image) {
      let formData = new FormData();
      formData.append("imageFile", image);
      this.bookService.setBookImage(book, formData).subscribe(
        response => {
          console.log(response);
          this.loadBooks();
        },
        error => alert('Error uploading book image: ' + error)
      );
    }
  }

  userImage(user: User) {
    return '/api/admin/users/' + user.id + '/image';
  }

  offerImage(offer: Offer) {
    return '/api/offers/' + offer.id + '/image';
  }

  bookImage(book: Book) {
    if (book.image && book.id) {
      return this.bookService.getImage(book.id)
    }
    else return
  }

}
