import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {


  constructor() { }

  ngOnInit() { }


  register(formData: NgForm) {
    console.log(formData.value);
  }

}

class RegisterObj {
  constructor(

  ) {}
}
