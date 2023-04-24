import { Component } from '@angular/core';
import { LoginService } from '../../../services/login.service';

@Component({
    selector: './register',
    templateUrl: './register.component.html'
})
export class RegisterComponent {
    constructor(public loginService: LoginService) {
    }

    register(event: { username: string, password: string, email: string }): void {
        this.loginService.register(event.username, event.password, event.email)
    }
}
