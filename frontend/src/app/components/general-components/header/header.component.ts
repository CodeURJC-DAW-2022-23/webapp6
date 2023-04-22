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

  constructor(
    private router: Router,
    public LoginService: LoginService) {
  }

  ngOnInit() {

  }




}
