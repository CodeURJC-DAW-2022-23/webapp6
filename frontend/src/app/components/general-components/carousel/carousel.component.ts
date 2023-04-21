import { Component, OnInit } from '@angular/core';
import { SlidesOutputData,OwlOptions } from 'ngx-owl-carousel-o';
import { HttpClient } from '@angular/common/http';
import { throwError } from 'rxjs';
import { Book } from 'src/app/models/book.model';
import { BookService } from 'src/app/services/book.service';
import { LoginService } from 'src/app/services/login.service';

export interface PhotosApi {
  albumId?: number;
  id?: number;
  title?: string;
  url?: string;
  thumbnailUrl?: string;
}

@Component({
  selector: 'carousel',
  templateUrl: './carousel.component.html'
})
export class Carousel implements OnInit  {

  books: Book[] | undefined;
  load: boolean | undefined;
  apiData: PhotosApi | undefined;
  limit: number = 10; // <==== Edit this number to limit API results
  customOptions: OwlOptions = {
    loop: true,
    autoplay: true,
    center: true,
    dots: true,
    autoHeight: true,
    autoWidth: true,
    responsive: {
      0: {
        items: 1,
      },
      400: {
        items: 2,
      },
      760: {
        items: 3
      },
      1000: {
        items: 4
      }
    }
  } 

  constructor(private readonly http: HttpClient, public bookService: BookService, public loginService: LoginService) {
    
  }

  ngOnInit() {
    this.getRecommended()
    this.fetch()
  }

  getRecommended() {
    this.bookService.getRecommendedBooks().subscribe(
        books => this.books = books,
        error => console.log(error)
    )
  }

  fetch() {
    const api = `https://jsonplaceholder.typicode.com/albums/1/photos?_start=0&_limit=${this.limit}`;
    const http$ = this.http.get<PhotosApi>(api);

    http$.subscribe(
      res => this.apiData = res,
      err => throwError(err)
    )
  }

  activeSlides?: SlidesOutputData;
  getPassedData(data: SlidesOutputData) {
    this.activeSlides = data;
    console.log(this.activeSlides);
  }
  startDragging(event: any){
    console.log(event);
  }
  getData(data: SlidesOutputData) {
    console.log(data);
  }

  getImage(book: Book) {
    if (book.id != undefined){
      return this.bookService.getImage(book.id);
    }
    else{
      return "";
    }
  }
  
  dynamicSlides = [
     {
       id: 1,
       src:'https://via.placeholder.com/600/92c952',
       alt:'Side 1',
       title:'Side 1'
     },
     {
       id: 2,
       src:'https://via.placeholder.com/600/771796',
       alt:'Side 2',
       title:'Side 2'
     },
     {
       id: 3,
       src:'https://via.placeholder.com/600/24f355',
       alt:'Side 3',
       title:'Side 3'
     },
     {
       id: 4,
       src:'https://via.placeholder.com/600/d32776',
       alt:'Side 4',
       title:'Side 4'
     },
     {
       id: 5,
       src:'https://via.placeholder.com/600/f66b97',
       alt:'Side 5',
       title:'Side 5'
     }
   ]
   
  
}