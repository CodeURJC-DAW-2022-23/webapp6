import { ReviewService } from 'src/app/services/review.service';
import { Component, Input } from '@angular/core';
import { Review } from 'src/app/models/review.model';
import { Router } from '@angular/router';

@Component({
  selector: 'review-card',
  templateUrl: './review-card.component.html',
  styleUrls: ['./review-card.component.css'],
})
export class ReviewCard {
  @Input() reviewId: string | undefined;

  review: Review | undefined;

  constructor(private router: Router, private reviewService: ReviewService) {}

  reviewIdNumber(): number | undefined {
    return parseInt(this.reviewId || '', 10);
  }

  ngOnInit() {
    this.reviewService.getReview(this.reviewIdNumber()).subscribe(
      review => this.review = review,
      error => console.log(error)
    );
  }

}
