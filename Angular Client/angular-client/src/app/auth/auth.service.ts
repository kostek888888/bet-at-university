import { Injectable } from '@angular/core';
import { User } from '@firebase/auth-types';
import { Router } from '@angular/router';

@Injectable()
export class AuthService {

  user: User;
  loginMsg: string;

  constructor(private router: Router) {
    // angularFire.authState.subscribe(user => {
    //   this.user = user;
    // });
   }

  login(email: string, password: string) {
    // this.angularFire.auth.signInWithEmailAndPassword(email, password)
    //   .then(user => { // zwraca token o typie User
    //     this.router.navigate(['/home']);
    //   })
    //   .catch(err => {
    //   this.loginMsg = err;
    // });
  }

  register(email: string, password: string) {
    // this.angularFire.auth.createUserWithEmailAndPassword(email, password)
    //   .then(user => { // zwraca token
    //     console.log(user);
    //   })
    //   .catch(err => {
    //     this.loginMsg = err;
    //   });
  }

  logout() {
    // this.angularFire.auth.signOut();
    // this.router.navigate(['/login']);
  }

  clearLoginMsg() {
    this.loginMsg = null;
  }

}
