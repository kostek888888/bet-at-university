import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  ngOnInit(): void {
  }


  constructor(public authService: AuthService) {
  }

  login(formData: NgForm) {
    this.authService.login(formData.value.email, formData.value.password);
  }

  register(formData: NgForm) {
    this.authService.register(formData.value.email, formData.value.password);
  }
}
