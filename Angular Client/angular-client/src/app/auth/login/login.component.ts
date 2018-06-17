import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { NgForm } from '@angular/forms';
import { HttpService } from '../../http.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public facebookUserWriteToBaseMsg = '';
  ngOnInit(): void {
    if (this.authService.userId !== null) {
      this.router.navigate(['your-profile']);
    }
  }

  constructor(public authService: AuthService, private http: HttpService, private router: Router) {
  }

  login(formData: NgForm) {
    this.authService.login(formData.value.login, formData.value.password);
  }

  loginViaFacebook() {
    this.http.connectWithFacebook()
      .then((res) => {
        this.http.findUserByFacebookId(res.additionalUserInfo.profile.id).subscribe(isFacebookUserInDB => {
          if (isFacebookUserInDB === false) {
            console.log('Nie znaleziono Facebookowego usera w bazie. Dodaje');
            this.writeFacebookUserInDb(res);
            this.facebookUserWriteToBaseMsg = 'To było Twoje pierwsze logowanie przez Facebook.' +
                                              ' Zarejestrowaliśmy Cię na naszej stronie. Wciśnij przycisk ponownie aby się zalogować';
          } else {
            console.log('Fb user jest juz w bazie bazie');
            this.facebookUserWriteToBaseMsg = '';
          }

          this.http.postLoginFacebookUser(res.additionalUserInfo.profile.id).subscribe(isLogin => {
            if (isLogin) {
              console.log('Uzytkownik facebook zalogowany');
              window.location.reload();
            } else {
              console.log('Uzytkownik facebook niezalogowany');
            }
          });

        }); // subscribe findUserByFacebookId end


      })
      .catch((err) => console.log(err));
  }

 writeFacebookUserInDb(res) {
    const userFirstName = res.additionalUserInfo.profile.first_name;
    const userLastName = res.additionalUserInfo.profile.last_name;
    const userEmail = res.additionalUserInfo.profile.email;
    const userFacebookId = res.additionalUserInfo.profile.id;
    this.http.postWriteFacebookUserInDB(userFirstName, userLastName, userEmail, userFacebookId).subscribe(isWriteInDb => {
      if (isWriteInDb) {
        console.log('user z fb zapisany w bazie');
        return true;
      }
    });
  }
}
