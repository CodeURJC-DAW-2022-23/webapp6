import { Component, NgZone, OnInit, Input, Output, EventEmitter } from '@angular/core';
//import { CartService } from 'src/app/services/cart.service';
import { User } from 'src/app/models/user.model';
import { LoginService } from 'src/app/services/login.service';
//import { PostsService } from 'src/app/services/posts.service';
import { Router } from '@angular/router';

@Component({
  selector: 'header-main',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @Output()
  show = new EventEmitter();

  @Output()
  filterTags = new EventEmitter<{id: number, search: string}>();


  //Info usuario
  user: User | undefined;
  isLogged: boolean | undefined ;
  isAdmin: boolean | undefined;


  constructor(
    private router: Router,
    public LoginService: LoginService) {
  }

  ngOnInit() {
    this.isLogged = this.LoginService.isLogged();
    if (this.isLogged) {
      this.user = this.LoginService.currentUser();
      if (this.user != undefined)
        this.isAdmin = this.user.roles.includes("ROLE_ADMIN");
    }
  }

  logOut() {
    this.LoginService.logOut();
    this.router.navigate(['']).then(_ =>
      window.location.reload()
    );
  }


}
