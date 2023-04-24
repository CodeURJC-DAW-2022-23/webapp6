import { Component, ViewChild } from '@angular/core';
import { BookService } from 'src/app/services/book.service';
import { Book } from 'src/app/models/book.model';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent {

  constructor(private adminService: AdminService, private bookService: BookService) { }

  newBook: Book = { title: '', author: '', genre: '', image: false };

  @ViewChild("file")
  file: any;

  uploadBookImage(book: Book): void {
    const image = this.file.nativeElement.files[0];
    if (image) {
      let formData = new FormData();
      formData.append("imageFile", image);
      this.adminService.setBookImage(book, formData).subscribe(
        response => {
          console.log(response);
        },
        error => console.log('Error uploading book image: ' + error)
      );
    }
  }

  addNewBook() {
    this.adminService.addBook(this.newBook).subscribe(
      response => {
        console.log(response);
        this.uploadBookImage(response);
      },
      error => console.log(error)
    );
  }

  bookImage(book: Book) {
    if (book.image && book.id) {
      return this.bookService.getImage(book.id)
    }
    else return
  }

}
