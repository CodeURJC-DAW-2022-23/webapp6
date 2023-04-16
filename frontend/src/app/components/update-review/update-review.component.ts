import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'update-review',
  templateUrl: './update-review.component.html',
  styleUrls: ['./update-review.component.css']
})
export class UpdateReviewComponent {

constructor(private router: Router) { }

text = "Valor del texto de la reseña";
// get text review

}
