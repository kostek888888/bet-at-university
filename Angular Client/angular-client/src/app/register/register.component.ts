import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpService } from '../http.service';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerStatus: boolean ;
  checkLoginAvailability: boolean;
  constructor(private http: HttpService, private router: Router, private authService: AuthService) { }

  ngOnInit() { }


  register(formData: NgForm) {
    this.http.checkLoginAvailability(formData.value.login).subscribe(isAvailable => { // zwraca true jesli login wolny
      if (isAvailable === true) {
        this.http.postRegister(formData).subscribe(registerStatus => { // zwraca true jesli zarejestronwany
          if (registerStatus) {
            this.checkLoginAvailability = true;
            this.registerStatus = true;
           this.router.navigate(['login']);
          }
        });
      } else {
        this.checkLoginAvailability = false;
        this.registerStatus = false;
      }
    });
  }



}
