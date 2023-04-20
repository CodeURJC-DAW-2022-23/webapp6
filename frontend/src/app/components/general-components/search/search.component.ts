import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/models/book.model';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})

export class SearchComponent {

  bestPick: Book | undefined;
  booksPage = 0;
  categories = true;
  searchText: String = "";


  constructor(private router: Router, activatedRoute: ActivatedRoute, public bookService: BookService) {

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

    goSearch(searchText: String) {
        //this.router.navigate(['/books?searchtext=', searchText]);
        this.router.navigate(['/books?searchtext=Literatura']);
    }

    /*getSearchBooks(searchText:String, n: number): Observable<Page> {
        return this.httpClient.get("/books?searchtext=" + searchText + "&page=" + n) as Observable<Page>;
      }*/

    //https://localhost:8443/api/books?searchtext=Literatura&page=1

    goBestPick(bestPick: Book) {
        this.router.navigate(['/books', bestPick.id]);
    }

}
