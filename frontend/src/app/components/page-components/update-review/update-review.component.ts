import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ReviewService } from 'src/app/services/review.service';
import { Review } from 'src/app/models/review.model';

@Component({
  selector: 'update-review',
  templateUrl: './update-review.component.html',
  styleUrls: ['./update-review.component.css']
})
export class UpdateReviewComponent {

  @Input() reviewId: string | undefined;

  review: Review | undefined;
  originalText: string | undefined;
  text: string | undefined

  constructor(private router: Router, activatedRoute: ActivatedRoute, private reviewService: ReviewService) {
    const reviewId = activatedRoute.snapshot.params['idReview'];

    this.reviewService.getReview(reviewId).subscribe(
      review => {
        this.review = review;
        this.originalText = review.text;
      }
    );
  }

  reviewIdNumber(): number | undefined {
    return parseInt(this.reviewId || '', 10);
  }

  updateReview(): void {
    if (this.review?.id && this.originalText) {
      this.reviewService.updateReview(this.review.id, this.originalText).subscribe(
        review => {
          console.log("La reseña se ha actualizado correctamente");
          this.router.navigate(['/user-page']);
        },
        error => console.log("Ha ocurrido un error al actualizar la reseña")
      );
    } else {
      console.log("Error: falta el ID de la reseña o el texto de la reseña.");
    }
  }

}
