import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { LoginService } from './services/login.service';
import { Observable } from 'rxjs';
import { map, } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private loginService: LoginService, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    return this.loginService.reqIsLogged().pipe(map(logged => {
      if (logged) {
        return true;
      } else {
        this.router.navigate(['/login']);
        return false;
      }
    }));
  }
}

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {

  constructor(private loginService: LoginService, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    this.loginService.reqLogged();
    return this.loginService.reqIsLogged().pipe(map(logged => {
      if (logged && this.loginService.isAdmin()) {
        return true;
      } else {
        this.router.navigate(['/login']);
        return false;
      }
    }));
  }
}
