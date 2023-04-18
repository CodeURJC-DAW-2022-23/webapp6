import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {
  username: string = '';
  password: string = '';

  @Output() onLogIn = new EventEmitter<{username: string, password: string}>();

  submitLoginForm() {
    this.onLogIn.emit({username: this.username, password: this.password});
  }
}
