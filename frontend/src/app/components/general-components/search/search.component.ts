import { Component, EventEmitter, Output } from '@angular/core';
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

    @Output()
    searchText = new EventEmitter<String>;

    goSearch(searchWord: String) {
        this.searchText.emit(searchWord);
    }
  
    goBestPick(bestPick: Book) {
        this.router.navigate(['/books', bestPick.id]);
    }

}
