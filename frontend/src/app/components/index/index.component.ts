import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BookService } from 'src/app/services/book.service';
import { LoginService } from 'src/app/services/login.service';


@Component({
  selector: 'index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent {

    constructor(private router: Router, public bookService: BookService, public loginService: LoginService) {
    
    }

    loadIndexBooks(searchWord: String) {
            this.router.navigate(['/books', {searchWord}]);
        }
}
