import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LogInService } from './log-in.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private loginService:LogInService,private router:Router){}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.guardContent(state.url);
  }

  guardContent(url:string){
    if(this.loginService.isLoggedIn==true){
      console.log('Successful authentication')
      console.log(this.loginService.isLoggedIn)
      return true
    }
    else{
      return this.router.parseUrl('/register')
    }
  }
}
