import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.model';
import { Observable, of } from 'rxjs';
import { map, tap, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';

const BASE_URL = '/api/auth';

@Injectable({ providedIn: 'root' })
export class LoginService {

  logged: boolean | undefined;
  user: User | undefined;

  constructor(private http: HttpClient, private router: Router) {
    this.reqLogged();
  }

  reqIsLogged(): Observable<boolean> {
    return this.http.get<boolean>('/api/user/isLogged', { withCredentials: true }).pipe(
      tap(response => {
        console.log(response)
        this.logged = response;
      }),
      catchError(error => {
        console.error('Error when asking if logged: ' + JSON.stringify(error));
        this.logged = false;
        return of(false);
      })
    );
  }

  reqLogged() {
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
        response => {
          this.reqLogged(),
            this.router.navigate(['/user-page']);
        },
        (error) => console.log("Credenciales incorrectas")
      );
  }

  register(name: string, password: string, email: string) {
    this.http.post(BASE_URL + "/register", { name: name, password: password, email: email }, { withCredentials: true })
      .subscribe(
        (response) => this.router.navigate(['/login']),
        (error) => this.router.navigate(['/login'])
      );
  }


  logOut() {
    if (this.logged) {
      return this.http.post(BASE_URL + '/logout', { withCredentials: true })
        .subscribe((resp: any) => {
          console.log("LOGOUT: Successfully");
          console.log("Sesión cerrada correctamente");
          this.router.navigate(['/index']),
            this.logged = false;
          this.user = undefined;
        });
    } else {
      return "";
    }
  }

  isLogged() {
    return this.logged;
  }

  isAdmin() {
    return this.logged && this.currentUser()?.name === "admin";
  }

  currentUser() {
    return this.user;
  }
}

