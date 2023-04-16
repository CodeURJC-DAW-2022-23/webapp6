import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { Review } from '../../../models/review.model';

@Component({
  selector: 'upload-review',
  templateUrl: './upload-review.component.html',
  styleUrls: ['./upload-review.component.css']
})
export class UploadReviewComponent {

  constructor(private router: Router) { }

  newReview(id: number) {
    // Call to services to create de review
    this.router.navigate(['/books', id]);
  }

}
