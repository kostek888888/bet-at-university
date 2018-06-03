import { Component, OnInit } from '@angular/core';
import { BetModel, ConvertBetModel } from '../../models/betModel';
import { AuthService } from '../../auth/auth.service';
import { HttpService } from '../../http.service';

@Component({
  selector: 'app-bets-history',
  templateUrl: './bets-history.component.html',
  styleUrls: ['./bets-history.component.css']
})
export class BetsHistoryComponent implements OnInit {

  formObj = new FormModel();
  betsArray: Array<BetModel>;
  constructor(private http: HttpService, public authService: AuthService) { }

  ngOnInit() {
    this.getNoneActiveBets();
    console.log(this.betsArray);
  }

  getSearchBets() {
    this.getNoneActiveBets();
  }



  getNoneActiveBets() {
      if (this.formObj.filter === 'noFilter') {
        this.http.postActiveBets(this.authService.userId.toString()).subscribe(activeBetsArrayResp => {
          console.log(activeBetsArrayResp);
          this.betsArray = ConvertBetModel.toBetModel(JSON.stringify(activeBetsArrayResp));
          this.betsArray = this.betsArray.filter(bet => bet.betResult === 0 || bet.betResult === 1 );
        });
        this.betsArray = this.sortMatchesByDate(this.betsArray);
      }
    if (this.formObj.filter === 'searchWon') {
      this.http.postActiveBets(this.authService.userId.toString()).subscribe(activeBetsArrayResp => {
        console.log(activeBetsArrayResp);
        this.betsArray = ConvertBetModel.toBetModel(JSON.stringify(activeBetsArrayResp));
        this.betsArray = this.betsArray.filter(bet => bet.betResult === 1);
      });
      this.betsArray = this.sortMatchesByDate(this.betsArray);
    }
    if (this.formObj.filter === 'searchLost') {
      this.http.postActiveBets(this.authService.userId.toString()).subscribe(activeBetsArrayResp => {
        console.log(activeBetsArrayResp);
        this.betsArray = ConvertBetModel.toBetModel(JSON.stringify(activeBetsArrayResp));
        this.betsArray = this.betsArray.filter(bet => bet.betResult === 0);
      });
      this.betsArray = this.sortMatchesByDate(this.betsArray);
    }
  }

  getBetRateToTable(betType: string, betId: string): string {
    let finalRate: string;
    const betObj = this.betsArray.find(bet => bet.id === parseInt(betId, 10));
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
      case '1': betTypeString = 'Wygrana gospodarzy:'; break;
      case '2': betTypeString = 'Wygrana gości:'; break;
      case 'X': betTypeString = 'Remis:'; break;
      case '1X': betTypeString = 'Wygrana gosp. lub remis:'; break;
      case '2X': betTypeString = 'Wygrana gości lub remis:'; break;
      case '12': betTypeString = 'Wygrana gosp. lub gości:'; break;
      default: betTypeString = 'parseBetTypeToString() in your-profile.component.ts: not match parse';
    }
    return betTypeString;
  }

  parseStatusToString(betType: string): string {
    let betTypeString: string;
    switch (betType) {
      case '0': betTypeString = 'Przegrany'; break;
      case '1': betTypeString = 'Wygrany'; break;
      case '2': betTypeString = 'W trakcie'; break;
      default: betTypeString = 'parseStatusToString() in bets-history.component.ts: not match parse';
    }
    return betTypeString;
  }

  // sortowanie meczy w tabeli, najnowsze na początek
  sortMatchesByDate(betsArray: Array<BetModel>): Array<BetModel> {
    betsArray.sort((bet1, bet2): number => {
      if (bet1.buyDateAndTime > bet2.buyDateAndTime) { return -1; }
      if (bet1.buyDateAndTime < bet2.buyDateAndTime) { return 1; }
      return 0;
    });
    return betsArray;
  }
}

export class FormModel {
  searchText?: string;
  filter = 'noFilter';
}
