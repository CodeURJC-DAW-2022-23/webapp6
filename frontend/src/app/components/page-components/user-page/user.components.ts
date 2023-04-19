import {Component} from '@angular/core';
import {LoginService} from '../../../services/login.service';

@Component({
    selector: './user',
    templateUrl: './user.component.html'
})
export class UserComponent {

    constructor(public loginService: LoginService) {
    }

    /* logIn(event: {username: string, password: string}): void {
        this.loginService.logIn(event.username, event.password);
    }

    logOut(): void {
        this.loginService.logOut();
    } */

}
