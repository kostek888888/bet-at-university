import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  ngOnInit(): void {
  }


  constructor() {
  }

  login(formData: NgForm) {
    // this.authService.login(formData.value.email, formData.value.password);
  }

  register(formData: NgForm) {
    // this.authService.register(formData.value.email, formData.value.password);
  }
}
