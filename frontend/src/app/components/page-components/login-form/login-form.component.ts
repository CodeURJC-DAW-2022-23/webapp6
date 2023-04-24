import { Component, EventEmitter, Output } from '@angular/core';
import { LoginService } from '../../../services/login.service';

@Component({
  selector: 'login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {
  username: string = '';
  password: string = '';

  constructor(public loginService: LoginService) { }

  @Output() onLogIn = new EventEmitter<{ username: string, password: string }>();

  submitLoginForm() {
    this.onLogIn.emit({ username: this.username, password: this.password });
  }

  logOut() {
    this.loginService.logOut();
  }
}
