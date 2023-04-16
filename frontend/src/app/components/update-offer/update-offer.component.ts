import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'update-offer',
  templateUrl: './update-offer.component.html',
  styleUrls: ['./update-offer.component.css']
})
export class UpdateOfferComponent {

  constructor(private router: Router) { }

  editorial = "Editorial de la oferta";
  // get editorial offer

  text = "Descripcion de la oferta";
  // get description offer

  price = "12";
  // get price offer

}
