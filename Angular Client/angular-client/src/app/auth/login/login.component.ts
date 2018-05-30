import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  ngOnInit(): void {
  }


  constructor(public authService: AuthService, private router: Router) {
  }

  login(formData: NgForm) {
    this.authService.login(formData.value.login, formData.value.password);
  }

  register(formData: NgForm) {
    this.authService.register(formData.value.login, formData.value.password);
  }

  redirectToRegister() {
    this.router.navigate(['/register']);
  }
}
