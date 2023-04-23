import { Component, ViewChild } from '@angular/core';
import { Review } from 'src/app/models/review.model';
import { AdminService } from 'src/app/services/admin.service';
import { ReviewService } from 'src/app/services/review.service';

@Component({
  selector: 'reviews-table',
  templateUrl: './reviews-table.component.html',
  styleUrls: ['../admin-page.component.css']
})
export class ReviewsTableComponent {

  reviews: Review[];
  loadedPage = 0;
  loadMoreButton = true;

  @ViewChild("file")
  file: any;

  constructor(private adminService: AdminService, private reviewService: ReviewService) {
    this.reviews = [];
  }

  ngOnInit() {
    this.loadReviews(this.loadedPage);
  }

  nextPage() {
    this.loadedPage += 1;
    this.loadReviews(this.loadedPage)
  }

  loadReviews(page: number) {
    this.reviewService.getReviewsPaginated(page).subscribe(
      loadedReviews => {
        this.reviews = this.reviews.concat(loadedReviews.content);
        this.loadMoreButton = loadedReviews.numberOfElements != 0;
      },
      error => console.log(error)
    );
  }

  reload() {
    for (var i = 0; i <= this.loadedPage; i++) {
      this.reviews = [];
      this.loadReviews(i);
    }
  }

  updateReview(review: Review) {
    this.adminService.updateReview(review).subscribe(
      _ => {},
      error => console.error(error)
    );
  }

  deleteReview(review: Review) {
    this.adminService.deleteReview(review).subscribe(
      _ => this.reviews.splice(this.reviews.indexOf(review), 1),
      error => console.error(error)
    );
  }

}
