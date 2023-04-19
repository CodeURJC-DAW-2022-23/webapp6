import { OfferService } from 'src/app/services/offer.service';
import { Component, Input } from '@angular/core';
import { Book } from 'src/app/models/book.model';
import { Router } from '@angular/router';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'book-card',
  templateUrl: './book-card.component.html',
  styleUrls: ['./book-card.component.css'],
})
export class BookCard {
  @Input() bookId: string | undefined;

  book: Book | undefined;

  constructor(private router: Router, private bookService: BookService) {}

  bookIdNumber(): number | undefined {
    return parseInt(this.bookId || '', 10);
  }

  ngOnInit() {
    this.bookService.getBook(this.bookIdNumber()).subscribe(
      book => this.book = book,
      error => console.log(error)
    );
  }

  getImage(book: Book) {
    if (book.id != undefined){
      return this.bookService.getImage(book.id);
    }
    else{
      return "";
    }
  }

}
