import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.model';
import { Observable, Subscription, throwError } from 'rxjs';

const BASE_URL = '/api/auth';

@Injectable({ providedIn: 'root' })
export class LoginService {

    logged: boolean | undefined;
    user: User | undefined;

    constructor(private http: HttpClient) {
        this.reqIsLogged();
    }

    reqIsLogged() {

        this.http.get('/api/user/', { withCredentials: true }).subscribe(
            response => {
                this.user = response as User;
                this.logged = true;
            },
            error => {
                if (error.status != 404) {
                    console.error('Error when asking if logged: ' + JSON.stringify(error));
                }
            }
        );

    }

    logIn(name: string, pass: string) {

        this.http.post(BASE_URL + "/login", { username: name, password: pass }, { withCredentials: true })
            .subscribe(
                //(response) => this.reqIsLogged(), 
                (response) => alert("Log In Correcto"), 
                (error) => alert("Wrong credentials")
            );

    }

    register(name: string, password: string, email: string) {

        //alert(`Username: ${name}\nPassword: ${password}\nEmail: ${email}`);
    
        this.http.post(BASE_URL + "/register", { name: name, password: password, email: email }, { withCredentials: true })
            .subscribe(
                (response) => this.reqIsLogged(),
                (error) => alert("Error al Registrarse")
            );
    
    }
    

    logOut() {

        return this.http.post(BASE_URL + '/logout', { withCredentials: true })
            .subscribe((resp: any) => {
                console.log("LOGOUT: Successfully");
                this.logged = false;
                this.user = undefined;
            });

    }

    isLogged() {
        return this.logged;
    }

    /* isAdmin() {
        return this.user && this.user.roles.indexOf('ADMIN') !== -1;
    } */

    currentUser() {
        return this.user;
    }
}

