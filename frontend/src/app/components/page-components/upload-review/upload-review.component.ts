import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Review } from '../../../models/review.model';
import { Book } from 'src/app/models/book.model';
import { BookService } from 'src/app/services/book.service';
import { ReviewTDO } from 'src/app/models/reviewTDO.model';
import { ReviewService } from 'src/app/services/review.service';

@Component({
  selector: 'upload-review',
  templateUrl: './upload-review.component.html',
  styleUrls: ['./upload-review.component.css']
})
export class UploadReviewComponent {

  book: Book | undefined;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public bookService: BookService, public reviewService: ReviewService) {

    const id = activatedRoute.snapshot.params['idBook'];

    this.bookService.getBook(id).subscribe(
      book => this.book = book,
      error => console.log(error)
    );
  }

  newReview: ReviewTDO = {text:""};

  addNewReview(book: Book) {
    if (book.id != undefined){
    this.reviewService.addReview(book.id, this.newReview).subscribe(
      response => {
        console.log(response);
        alert("La reseña se ha subido correctamente");
        this.router.navigate(['/books', book.id]);
      },
      error => console.log(error)
    );
    }
  }



}
