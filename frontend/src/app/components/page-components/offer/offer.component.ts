import { User } from 'src/app/models/user.model';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Offer } from 'src/app/models/offer.model';
import { LoginService } from 'src/app/services/login.service';
import { OfferService } from 'src/app/services/offer.service';

@Component({
  selector: 'offer',
  templateUrl: './offer.component.html',
  styleUrls: ['./offer.component.css']
})
export class OfferComponent {

  offer: Offer | undefined;
  isOwn: boolean | undefined;
  user: User | undefined;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public offerService: OfferService, public loginService: LoginService) {

    const id = activatedRoute.snapshot.params['idOffer'];

    this.offerService.getOffer(id).subscribe(
      offer => {
        this.offer = offer,
        this.user = this.loginService.currentUser();
        this.isOwn = this.user?.name === this.offer?.seller.name;
      },
      error => console.log(error)
    );

    this.user = this.loginService.currentUser();
    this.isOwn = this.user?.name === this.offer?.seller.name;
  }

  getImage(offer: Offer) {
    if (offer.id != undefined){
      return this.offerService.getImage(offer.id);
    }
    else{
      return "";
    }
  }

  buyOffer(offer: Offer) {
    this.router.navigate(['/offers', offer.id, 'checkout']);
  }

  editOffer(offer: Offer) {
    this.router.navigate(['/offers', offer.id, 'update-offer']);
  }

  goLogin(){
    this.router.navigate(['/login'])
  }

}
