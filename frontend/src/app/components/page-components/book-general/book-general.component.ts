import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
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
  searchText: String | undefined;


    constructor(private route: ActivatedRoute, public bookService: BookService, activatedRoute: ActivatedRoute, public loginService: LoginService) {
        const searchWord = activatedRoute.snapshot.params['searchWord'];
        if (searchWord == null){
            this.loadBooks("");
        } else {
            this.loadBooks(searchWord);
        }
    }

    ngOnInit() {
        this.route.params.subscribe(params => {
          const myParam = params['searchWord'];
        });
      }

    loadBooks(searchText: String) {
        this.booksPage = 0;
        this.searchText = searchText;
        this.booksButton = true;
        this.bookService.getSearchBooks( searchText, this.booksPage).subscribe(
                books => this.books = books,
                error => console.log(error)
            );
    }

    loadMoreBooks(n: number, searchText?: String) {
        this.booksPage = n;
        if (searchText == null) {
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
        } else {
            this.bookService.getSearchBooks( searchText, n).subscribe(
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
}
