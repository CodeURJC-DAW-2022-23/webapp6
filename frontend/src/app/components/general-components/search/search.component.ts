import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/models/book.model';
import { Page } from 'src/app/models/page.model';
import { BookService } from 'src/app/services/book.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})

export class SearchComponent {

  bestPick: Book | undefined;
  booksPage = 0;
  categories = true;


  constructor(private router: Router, activatedRoute: ActivatedRoute, public bookService: BookService, public loginService: LoginService) {

    this.bookService.getBestPick().subscribe(
        bestPick => this.bestPick = bestPick,
        error => console.log(error)
      );
    }

    hide() {
        if (this.categories){
            this.categories = false;
        } else {
            this.categories = true;
        }
    }

    goSearch(book: Book) {
        this.router.navigate(['/books', book.id, 'upload-review']);
    }
    //https://localhost:8443/api/books?searchtext=Literatura&page=1

    goBestPick(bestPick: Book) {
        this.router.navigate(['/books', bestPick.id]);
    }

}
