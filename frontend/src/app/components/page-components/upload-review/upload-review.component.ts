import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Review } from '../../../models/review.model';
import { Book } from 'src/app/models/book.model';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'upload-review',
  templateUrl: './upload-review.component.html',
  styleUrls: ['./upload-review.component.css']
})
export class UploadReviewComponent {

  book: Book | undefined;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public bookService: BookService) {

    const id = activatedRoute.snapshot.params['idBook'];

    this.bookService.getBook(id).subscribe(
      book => this.book = book,
      error => console.log(error)
    );
  }

  newReview(book: Book) {
    // Call to services to create de review
    this.router.navigate(['/books', book.id]);
  }

}
