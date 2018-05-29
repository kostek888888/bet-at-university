import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpService } from '../http.service';


@Injectable()
export class AuthService {

  isLogged: number;
  loginMsg: string;

  constructor(private httpService: HttpService, private router: Router) {
    console.log(this.isLogged);
   }

   // zadanie logowania zwraca true jesli zalogowany lub false jesli nie
  login(login: string, password: string) {
    this.httpService.postLogin(login, password).subscribe(userIdResponse => {
      if (userIdResponse) {
        this.isLogged = userIdResponse;
      } else {
        this.isLogged = null;
      }
      console.log('userId in db: ' + this.isLogged);
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
    this.httpService.postLogout();
  }

  clearLoginMsg() {
    this.loginMsg = null;
  }

}
