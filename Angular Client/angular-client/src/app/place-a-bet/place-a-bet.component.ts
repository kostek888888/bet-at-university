import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { AuthService } from '../auth/auth.service';
import { MatchModel, ConvertMatchModel } from '../models/matchModel';
import { BetsRatesModel, ConvertBetsRateModel } from '../models/betsRatesModel';
import { UserStatisticModel, ConvertToUserStatistic } from '../models/userModel';
import { Router } from '@angular/router';

@Component({
  selector: 'app-place-a-bet',
  templateUrl: './place-a-bet.component.html',
  styleUrls: ['./place-a-bet.component.css']
})
export class PlaceABetComponent implements OnInit {

  matchesToBetArray: Array<MatchModel>;
  betsRatesArray: Array<BetsRatesModel>;
  userStatistic: UserStatisticModel;
  betStatusInfo: boolean;
  notEnoughMoneyInfo: string;
  constructor(public authservice: AuthService, private http: HttpService, private router: Router) { }

  ngOnInit() {
    this.getBetsRates();
    this.getUserStatistic();
    this.getMatchesToBet();
  }

  getBetsRates() {
    this.http.getBetsRatesHttpService().subscribe(betsRatesFromDB => {
      this.betsRatesArray = ConvertBetsRateModel.toBetsRatesModel(JSON.stringify(betsRatesFromDB));
      console.log(this.betsRatesArray);
    });
  }

  getMatchesToBet() {
    this.http.getMatchToBetHttpService().subscribe(matchesFromDB => {
      this.matchesToBetArray = ConvertMatchModel.toMatchModel(JSON.stringify(matchesFromDB));
    });
  }

  getUserStatistic() {
    this.http.postUserStatisticFromId(this.authservice.userId.toString()).subscribe(userStatisticObj => {
      this.userStatistic = ConvertToUserStatistic.toUserStatisticModel(JSON.stringify(userStatisticObj));
    });
  }

  betMatch(matchId: string, betType: string, moneyInsertInput: string) {
    if (parseFloat(moneyInsertInput) <= this.userStatistic.accountBalance) {
      console.log(matchId + ' ' + this.authservice.userId + ' ' + betType + ' ' + moneyInsertInput);
      this.http.postBetMatch(matchId, this.authservice.userId.toString(), betType, moneyInsertInput).subscribe(betStatus => {
        this.betStatusInfo = betStatus;
        this.router.navigate(['your-profile']);
        this.notEnoughMoneyInfo = '';
      });
    } else {
      this.notEnoughMoneyInfo = 'Masz zbyt mało pieniędzy aby postawić zakład';
    }
  }

  getColor(betRate: number): string {
    if (betRate < 2.0) {
      return 'green';
    } else if (betRate >= 2 && betRate < 2.5) {
      return '#DAA520';
    } else {
      return 'darkred';
    }
  }

  increaseAccountBalance(money = '500') {
    const userId = this.userStatistic.id.toString();
    this.http.postIncreaseAccountBalance(userId, money).subscribe(userStatisticObj => {
      this.userStatistic = userStatisticObj;
    });
  }
}
