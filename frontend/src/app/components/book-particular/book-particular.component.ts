import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'book-particular',
  templateUrl: './book-particular.component.html',
  styleUrls: ['./book-particular.component.css']
})
export class BookParticularComponent {
  constructor(private router: Router) { }

  uploadOffer(id: number) {
    this.router.navigate(['/books', id, 'upload-offer']);
  }

  uploadReview(id: number) {
    // Call to services to create de offer
    this.router.navigate(['/books', id, 'upload-review']);
  }
}
