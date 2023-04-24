import { Component, ViewChild } from '@angular/core';
import { Book } from 'src/app/models/book.model';
import { BookService } from 'src/app/services/book.service';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'books-table',
  templateUrl: './books-table.component.html',
  styleUrls: ['../admin-page.component.css']
})
export class BooksTableComponent {

  books: Book[];
  loadedPage = 0;
  loadMoreButton = true;

  @ViewChild("file")
  file: any;

  constructor(private adminService: AdminService, private bookService: BookService) {
    this.books = [];
  }

  ngOnInit() {
    this.loadBooks(this.loadedPage);
  }

  nextPage() {
    this.loadedPage += 1;
    this.loadBooks(this.loadedPage)
  }

  loadBooks(page: number) {
    this.bookService.getBooksPaginated(page).subscribe(
      loadedBooks => {
        this.books = this.books.concat(loadedBooks.content);
        this.loadMoreButton = loadedBooks.numberOfElements != 0;
      },
      error => console.log(error)
    );
  }

  reload() {
    for (var i = 0; i <= this.loadedPage; i++) {
      this.books = [];
      this.loadBooks(i);
    }
  }

  updateBook(book: Book) {
    this.adminService.updateBook(book).subscribe(
      _ => {
        this.adminService.deleteBookImage(book);
        this.uploadBookImage(book);
      },
      error => console.error(error)
    );
  }

  deleteBook(book: Book) {
    this.adminService.deleteBook(book).subscribe(
      _ => this.books.splice(this.books.indexOf(book), 1),
      error => console.error(error)
    );
  }

  uploadBookImage(book: Book): void {
    const image = this.file.nativeElement.files[0];
    if (image) {
      let formData = new FormData();
      formData.append("imageFile", image);
      this.adminService.setBookImage(book, formData).subscribe(
        response => {
          console.log(response);
          this.reload();
        },
        error => console.log('Error uploading book image: ' + error)
      );
    }
  }

  bookImage(book: Book) {
    if (book.id) {
      return this.bookService.getImage(book.id); 
    }
    else return
  }
}
