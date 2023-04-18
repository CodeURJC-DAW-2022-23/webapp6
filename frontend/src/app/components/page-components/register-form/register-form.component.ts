import { Component, EventEmitter, Output } from '@angular/core';
import { LoginService } from '../../../services/login.service';

@Component({
  selector: 'register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent {
  
  username: string = '';
  password: string = '';
  email: string = '';

  constructor(public loginService: LoginService) { } 

  @Output() register = new EventEmitter<{username: string, password: string, email: string}>();

  
  submitRegisterForm() {
    console.log('Form submitted');
    this.register.emit({username: this.username, password: this.password, email: this.email});
  }

}
