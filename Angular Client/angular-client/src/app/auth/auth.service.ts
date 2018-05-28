import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpService } from '../http.service';
import { LoginModel } from '../models/userModel';

@Injectable()
export class AuthService {

  user: LoginModel;
  loginMsg: string;

  constructor(private httpService: HttpService, private router: Router) {
    console.log(this.user);
   }

  login(login: string, password: string) {
    this.httpService.postLogin(login, password).subscribe(user => {
      this.user = user;
      console.log(this.user);
    });
  }

  register(email: string, password: string) {
  //   this.angularFire.auth.createUserWithEmailAndPassword(email, password)
  //     .then(user => { // zwraca token
  //       console.log(user);
  //     })
  //     .catch(err => {
  //       this.loginMsg = err;
  //     });
  //
}

  logout() {
    // this.angularFire.auth.signOut();
    // this.router.navigate(['/login']);
  }

  clearLoginMsg() {
    this.loginMsg = null;
  }

}
