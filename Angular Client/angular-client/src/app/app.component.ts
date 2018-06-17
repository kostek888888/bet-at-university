import { Component } from '@angular/core';
import { AuthService } from './auth/auth.service';
import { Router } from '@angular/router';
import { environment } from '../environments/environment';
import { HttpService } from './http.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  menuLogoURL = environment.config.manuLogoURL;
  constructor(public authService: AuthService, private router: Router, private http: HttpService) { }

  logout() {
    this.authService.logout();
    this.http.logoutFromFacebook();
    this.router.navigate(['/login']);
  }
}
