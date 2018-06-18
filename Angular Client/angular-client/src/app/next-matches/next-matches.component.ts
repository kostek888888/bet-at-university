import { Component, OnInit } from '@angular/core';
import { MatchModel, ConvertMatchModel } from '../models/matchModel';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-next-matches',
  templateUrl: './next-matches.component.html',
  styleUrls: ['./next-matches.component.css']
})
export class NextMatchesComponent implements OnInit {

  matchesArray: Array<MatchModel>;
  formObj = new FormModel();
  constructor(private httpService: HttpService) { }

  ngOnInit(): void {
    this.getFirstFiveMatches();
  }


  getFirstFiveMatches() {
    // print only 5 newest matches in table when page init
    this.httpService.getMatchToBet().subscribe(response => {
      this.matchesArray = ConvertMatchModel.toMatchModel(JSON.stringify(response));
      if (this.matchesArray.length >= 5) {
        this.matchesArray = this.matchesArray.slice(0, 5);
      }
      this.matchesArray = this.sortMatchesByDate(this.matchesArray);
    });
  }

  getSearchResult() {
    if (this.formObj.filter === 'noFilter') {
      this.httpService.getMatchToBet().subscribe(response => {
        this.matchesArray = ConvertMatchModel.toMatchModel(JSON.stringify(response));
        this.matchesArray = this.sortMatchesByDate(this.matchesArray);
      });
    } else if (this.formObj.filter === 'searchByDay') {
      this.httpService.getMatchToBet().subscribe(response => {
        this.matchesArray = ConvertMatchModel.toMatchModel(JSON.stringify(response));
        // fitrowanie po dacie
        this.matchesArray = this.matchesArray.filter(item => item.matchDateAndTime >= this.formObj.searchText);
        this.matchesArray = this.sortMatchesByDate(this.matchesArray);
      });
    } else if (this.formObj.filter === 'searchByTeamName') {
      this.httpService.getMatchToBet().subscribe(response => {
        this.matchesArray = ConvertMatchModel.toMatchModel(JSON.stringify(response));
        // fitrowanie po nazwie druzyny
        this.matchesArray = this.matchesArray.filter(item =>
          item.homeTeamId.name === this.formObj.searchText || item.awayTeamId.name === this.formObj.searchText
        );
        this.matchesArray = this.sortMatchesByDate(this.matchesArray);
      });
    } else { }
  }

  // sortowanie meczy w tabeli, najnowsze na początek
  sortMatchesByDate(matchesArray: Array<MatchModel>): Array<MatchModel> {
    matchesArray.sort((match1, match2): number => {
      if (match1.matchDateAndTime > match2.matchDateAndTime) { return -1; }
      if (match1.matchDateAndTime < match2.matchDateAndTime) { return 1; }
      return 0;
    });
    return matchesArray;
  }
}


export class FormModel {
  searchText?: string;
  filter = 'noFilter';
}
