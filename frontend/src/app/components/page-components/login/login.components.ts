/* import {Component} from '@angular/core';
import {LoginService} from '../../../services/login.service';

@Component({
    selector: './login',
    templateUrl: './login.component.html'
})
export class LoginComponent {

    constructor(public loginService: LoginService) {
    }

    logIn(event: any, name: string, password: string): void {

        event.preventDefault();

        this.loginService.logIn(name, password);
    }

    logOut(): void {
        this.loginService.logOut();
    }

} */

import {Component} from '@angular/core';
import {LoginService} from '../../../services/login.service';

@Component({
    selector: './login',
    templateUrl: './login.component.html'
})
export class LoginComponent {

    constructor(public loginService: LoginService) {
    }

    logIn(event: {username: string, password: string}): void {
        this.loginService.logIn(event.username, event.password);
    }

    logOut(): void {
        this.loginService.logOut();
    }

}
