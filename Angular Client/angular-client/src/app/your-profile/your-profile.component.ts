import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { UserStatisticModel, Convert } from '../models/userModel';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-your-profile',
  templateUrl: './your-profile.component.html',
  styleUrls: ['./your-profile.component.css']
})
export class YourProfileComponent implements OnInit {

  userStatistic: UserStatisticModel;
  constructor(public authservice: AuthService, private http: HttpService) {
    this.getUserStatistic();
   }

  ngOnInit() {

  }

  getUserStatistic() {
    this.http.postUserStatisticFromId(this.authservice.userId.toString()).subscribe(userStatisticObj => {
      this.userStatistic = Convert.toUserStatisticModel(JSON.stringify(userStatisticObj));
      console.log(this.userStatistic);
    });
  }

  increaseAccountBalance() {
    this.userStatistic.accountBalance = this.userStatistic.accountBalance + 500;
    console.log(this.userStatistic);
  }
}
