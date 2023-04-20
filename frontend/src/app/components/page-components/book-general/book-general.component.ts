import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Page } from 'src/app/models/page.model';
import { BookService } from 'src/app/services/book.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'book-general',
  templateUrl: './book-general.component.html',
  styleUrls: ['./book-general.component.css']
})
export class BookGeneralComponent {

  books: Page | undefined;
  booksPage = 0;
  booksButton = true;

    constructor(private router: Router, activatedRoute: ActivatedRoute, public bookService: BookService, public loginService: LoginService) {
        this.loadBooks();
    }


    loadBooks(searchText?: String) {
        if(searchText == null) {
            this.bookService.getSearchBooks( "", this.booksPage).subscribe(
                books => this.books = books,
                error => console.log(error)
            );
        } else {
            this.bookService.getSearchBooks( searchText, this.booksPage).subscribe(
                books => this.books = books,
                error => console.log(error)
            );
        }
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
}
