import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpService } from '../http.service';
import { CookieService } from 'ngx-cookie-service';


@Injectable()
export class AuthService {

  public userId: number;
  loginStatus: boolean;

  constructor(private httpService: HttpService, private cookieService: CookieService, private router: Router) {
    this.checkCookieExistAndInitUserIdVariable(); // on init check cookie
   }

   checkCookieExistAndInitUserIdVariable(): void {
     if (this.cookieService.check('bet_at_university_cookie') === true) {
       this.userId = parseInt(this.cookieService.get('bet_at_university_cookie'), 10);
     } else {
       console.log('auth.service.ts constructor: nie ma takiego ciasteczka');
     }
   }

  login(login: string, password: string) {
    this.httpService.postLogin(login, password).subscribe(cookieCreateStatus => {
      if (cookieCreateStatus) {
        this.userId = parseInt(this.cookieService.get('bet_at_university_cookie'), 10);
        this.router.navigate(['your-profile']);
      } else {
        this.userId = null;
        this.loginStatus = false;
      }
      console.log('userId in db: ' + this.userId);
    });
  }

  logout() {
    this.httpService.postLogout().subscribe(logoutStatus => {
      if (logoutStatus) {
        this.userId = null;
      }
      console.log('Logout status: ' + logoutStatus);
    });
  }


}
