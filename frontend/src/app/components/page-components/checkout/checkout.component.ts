import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent {

  constructor(private router: Router) { }

}
