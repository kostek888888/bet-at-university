import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { UserStatisticModel, Convert } from '../models/userModel';
import { HttpService } from '../http.service';
import { MatchModel, ConvertMatchModel } from '../models/matchModel';
import { BetsRatesModel, ConvertBetsRateModel } from '../models/betsRatesModel';
import { BetModel, ConvertBetModel } from '../models/betModel';

@Component({
  selector: 'app-your-profile',
  templateUrl: './your-profile.component.html',
  styleUrls: ['./your-profile.component.css']
})
export class YourProfileComponent implements OnInit {

  matchesToBetArray: Array<MatchModel>;
  userStatistic: UserStatisticModel;
  betsRatesArray: Array<BetsRatesModel>;
  activeBetsArray: Array<BetModel>;
  betStatusInfo: boolean;
  constructor(public authservice: AuthService, private http: HttpService) {
    this.getBetsRates();
    this.getUserStatistic();
    this.getMatchesToBet();
    this.getActiveBets();
   }

  ngOnInit() {

  }

  getUserStatistic() {
    this.http.postUserStatisticFromId(this.authservice.userId.toString()).subscribe(userStatisticObj => {
      this.userStatistic = Convert.toUserStatisticModel(JSON.stringify(userStatisticObj));
    });
  }

  increaseAccountBalance( money = '500') {
    const userId = this.userStatistic.id.toString();
    this.http.postIncreaseAccountBalance(userId, money).subscribe( userStatisticObj => {
    this.userStatistic = userStatisticObj;
    });
  }

  getMatchesToBet() {
    this.http.getMatchToBetHttpService().subscribe( matchesFromDB => {
      this.matchesToBetArray = ConvertMatchModel.toMatchModel(JSON.stringify(matchesFromDB));
    });
  }

  getBetsRates() {
    this.http.getBetsRatesHttpService().subscribe(betsRatesFromDB => {
      this.betsRatesArray = ConvertBetsRateModel.toBetsRatesModel(JSON.stringify(betsRatesFromDB));
      console.log(this.betsRatesArray);
    });
  }

  betMatch(matchId: string, betType: string, moneyInsertInput: string) {
    const moneyInsertedNumber = parseFloat(moneyInsertInput);
    if (parseFloat(moneyInsertInput) <= this.userStatistic.accountBalance) {
      console.log(matchId + ' ' + this.authservice.userId + ' ' + betType + ' ' + moneyInsertInput);
      this.http.postBetMatch(matchId, this.authservice.userId.toString(), betType, moneyInsertInput).subscribe(betStatus => {
        this.betStatusInfo = betStatus;
        window.location.reload();
      });
    }
  }

  getActiveBets() {
    this.http.postActiveBets(this.authservice.userId.toString()).subscribe(activeBetsArrayResp => {
      this.activeBetsArray = ConvertBetModel.toBetModel(JSON.stringify(activeBetsArrayResp));
    });
  }

  getBetRateToTable(betType: string, betId: string): string {
    let finalRate: string;
    const betObj = this.activeBetsArray.find(bet => bet.id === parseInt(betId, 10));
    switch (betObj.betType) {
      case '1': finalRate = betObj.betRate.homeTeamWinRate.toString(); break;
      case '2': finalRate = betObj.betRate.awayTeamWinRate.toString(); break;
      case 'X': finalRate = betObj.betRate.drawRate.toString(); break;
      case '1X': finalRate = betObj.betRate.homeTeamWinOrDrawRate.toString(); break;
      case '2X': finalRate = betObj.betRate.awayTeamWinOrDrawRate.toString(); break;
      case '12': finalRate = betObj.betRate.homeWinOrAwayWin.toString(); break;
    }
    return finalRate;
  }

  parseBetTypeToString(betType: string): string {
    let betTypeString: string;
    switch (betType) {
      case '1': betTypeString = 'Wygrana gospodarzy:';  break;
      case '2': betTypeString = 'Wygrana gości:'; break;
      case 'X': betTypeString = 'Remis:'; break;
      case '1X': betTypeString = 'Wygrana gosp. lub remis:'; break;
      case '2X': betTypeString = 'Wygrana gości lub remis:'; break;
      case '12': betTypeString = 'Wygrana gosp. lub gości:'; break;
      default: betTypeString = 'parseBetTypeToString() in your-profile.component.ts: not match parse';
    }
    return betTypeString;
  }
}
