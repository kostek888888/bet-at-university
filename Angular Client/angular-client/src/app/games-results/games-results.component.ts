import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { MatchModel, Convert } from '../models/matchModel';

@Component({
  selector: 'app-games-results',
  templateUrl: './games-results.component.html',
  styleUrls: ['./games-results.component.css']
})
export class GamesResultsComponent implements OnInit {

  matchModelObj: Array<MatchModel>;
  formObj = new FormModel();
  constructor(private httpService: HttpService) { }

  ngOnInit() {
    // this.searchOnInit();
    console.log(this.matchModelObj);
  }

  searchOnInit() {
    this.httpService.getMatch().subscribe(response => {
      this.matchModelObj = Convert.toMatchModel(JSON.stringify(response));
      console.log(this.matchModelObj);
    });
  }

  getSearchResult() {
    if (this.formObj.filter === 'noFilter') {
      this.httpService.getMatch().subscribe(response => {
        this.matchModelObj = Convert.toMatchModel(JSON.stringify(response));
      });
    } else if (this.formObj.filter === 'searchByDay') {
      this.httpService.getMatch().subscribe(response => {
        this.matchModelObj = Convert.toMatchModel(JSON.stringify(response));

        // fitrowanie po dacie
        this.matchModelObj = this.matchModelObj.filter(item => item.matchDateAndTime >= this.formObj.searchText);
        // console.log(this.matchModelObj[0].matchDateAndTime + 'obj date');
        // console.log(this.formObj.searchText + 'form date');
        // console.log(this.matchModelObj);
      });
    } else if (this.formObj.filter === 'searchByTeamName') {
        this.httpService.getMatch().subscribe(response => {
        this.matchModelObj = Convert.toMatchModel(JSON.stringify(response));

        // fitrowanie po nazwie druzyny
        this.matchModelObj = this.matchModelObj.filter(item =>
          item.homeTeamId.name === this.formObj.searchText || item.awayTeamId.name === this.formObj.searchText);
      });
    } else { }
  }

}


class FormModel {
  searchText?: string;
  filter = 'noFilter';
}
