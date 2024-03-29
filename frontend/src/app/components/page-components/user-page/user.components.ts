import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/models/book.model';
import { User } from 'src/app/models/user.model';
import { Page } from 'src/app/models/page.model';
import { BookService } from 'src/app/services/book.service';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/services/user.service';
import { ReviewService } from 'src/app/services/review.service';

@Component({
  selector: './user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {

  book: Book | undefined;
  reviews: Page | undefined;
  offers: Page | undefined;
  favorites: Page | undefined;
  pageOffers: number = 0;
  pageHistorial: number = 0;
  pageFavorites: number = 0;
  pageReviews: number = 0;
  showButtonOffers: boolean = true;
  showButtonHistorial: boolean = true;
  showButtonReviews: boolean = true;
  showButtonFavorites: boolean = true;
  logged: boolean | undefined;
  isFavorite: boolean | undefined;
  user: User | undefined;
  historial: Page | undefined;
  userImage: any;
  newEmail: string | undefined;

  constructor(private router: Router, activatedRoute: ActivatedRoute,
    public bookService: BookService, public loginService: LoginService,
    public userService: UserService, private reviewService: ReviewService) {

    this.userService.getUser().subscribe(
      user => this.user = user,
      error => console.log(error)
    );

    this.userService.getUserImage().subscribe(
      image => {
        let reader = new FileReader();
        reader.addEventListener("load", () => {
          this.userImage = reader.result;
        }, false);

        if (image) {
          reader.readAsDataURL(image);
        }
      },
      error => console.log(error)
    );

    this.userService.getUserFavoritesPaginated(0).subscribe(
      favorites => this.favorites = favorites,
      error => console.log(error)
    );

    this.userService.getUserReviewsPaginated(0).subscribe(
      reviews => this.reviews = reviews,
      error => console.log(error)
    );

    this.userService.getUserOffersPaginated(0).subscribe(
      offers => this.offers = offers,
      error => console.log(error)
    );

    this.userService.getUserHistorialPaginated(0).subscribe(
      offers => this.historial = this.historial,
      error => console.log(error)
    );

    this.loadUserHistorial();
  }

  ngOnInit() {
    this.userService.getUser().subscribe(
      user => this.user = user,
      error => console.log(error)
    );
  }

  loadUserHistorial() {
    this.userService.getUserHistorialPaginated(0).subscribe(
      historial => {
        this.historial = historial;
      },
      error => console.log(error)
    );
  }

  loadUserOffers() {
    this.userService.getUserOffers().subscribe(
      userOffersPage => {
        if (userOffersPage.numberOfElements != 0) {
          this.offers = userOffersPage;
        } else {
          this.showButtonOffers = false;
        }
      },
      error => console.log(error)
    );
  }

  loadMoreUserReviews(n: number) {
    this.pageReviews = n;
    this.userService.getUserReviewsPaginated(n).subscribe(
      newReviews => {
        if (this.reviews != undefined && newReviews.numberOfElements != 0) {
          this.reviews.content = this.reviews.content.concat(newReviews.content);
        } else {
          this.showButtonReviews = false;
        }
      },
      error => console.log(error)
    );
  }

  loadMoreUserOffers(n: number) {
    this.pageOffers = n;
    this.userService.getUserOffersPaginated(n).subscribe(
      newOffers => {
        if (this.offers != undefined && newOffers.numberOfElements != 0) {
          this.offers.content = this.offers.content.concat(newOffers.content);
        } else {
          this.showButtonOffers = false;
        }
      },
      error => console.log(error)
    );
  }

  loadMoreUserFavorites(n: number) {
    this.pageFavorites = n;
    this.userService.getUserFavoritesPaginated(n).subscribe(
      newFavorites => {
        if (this.favorites != undefined && newFavorites.numberOfElements != 0) {
          this.favorites.content = this.favorites.content.concat(newFavorites.content);
        } else {
          this.showButtonFavorites = false;
        }
      },
      error => console.log(error)
    );
  }

  loadMoreUserHistorials(n: number) {
    this.pageHistorial = n;
    this.userService.getUserHistorialPaginated(n).subscribe(
      newHistorial => {
        if (this.historial != undefined && newHistorial.numberOfElements != 0) {
          this.historial.content = this.historial.content.concat(newHistorial.content);
        } else {
          this.showButtonHistorial = false;
        }
      },
      error => console.log(error)
    );
  }

  navigateToUpEditProfile() {
    this.router.navigate(['/update-profile']);
  }

  uploadOffer(book: Book) {
    this.router.navigate(['/books', book.id, 'upload-offer']);
  }

  uploadReview(book: Book) {
    this.router.navigate(['/books', book.id, 'upload-review']);
  }

  goLogin() {
    this.router.navigate(['/login'])
  }

  addFavorite(book: Book) {
    if (book.id != undefined) {
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
    if (book.id != undefined) {
      this.bookService.deleteFavorite(book.id).subscribe(
        response => {
          console.log(response);
          this.isFavorite = false;
        },
        error => console.log(error)
      );
    }
  }

  navigateToUploadReview(id: number) {
    this.router.navigate(['/update-review', id]);
  }

  deleteReview(id: number): void {
    if (confirm("¿Estás seguro de que deseas eliminar esta reseña?")) {
      this.reviewService.deleteReview(id).subscribe(
        response => {
          console.log("La reseña ha sido eliminada.");
          this.userService.getUserReviewsPaginated(this.pageReviews).subscribe(
            reviews => this.reviews = reviews,
            error => console.log(error)
          );
        },
        error => {
          console.log(error);
          console.log("Error al eliminar la reseña.");
        }
      );
    }
  }
}
