import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { FixturesArray, Convert } from '../models/fixturesModel';

@Component({
  selector: 'app-games-results',
  templateUrl: './games-results.component.html',
  styleUrls: ['./games-results.component.css']
})
export class GamesResultsComponent implements OnInit {

  gamesResults: FixturesArray['fixtures'];
  formObj = new FormModel();
  constructor(private httpService: HttpService) { }

  ngOnInit() {
    this.getSearchResult();
    console.log(this.gamesResults);
  }

  getSearchResult() {
    if (this.formObj.filter === 'searchMatchday') {
      this.httpService.getFixturesByMatchday(this.formObj.searchText).subscribe(response => {
        this.gamesResults = Convert.toFixtures(JSON.stringify(response.fixtures));
        console.log(response);
      });
    } else {
      this.httpService.getPastFixturesByTeam(this.formObj.searchText, '3').subscribe(response => {
        this.gamesResults = Convert.toFixtures(JSON.stringify(response.fixtures));
        console.log(response);
      });

      this.httpService.getNextFixturesByTeam(this.formObj.searchText, '3').subscribe(response => {
        this.gamesResults = Convert.toFixtures(JSON.stringify(response.fixtures));
        console.log(response);
      });
    }
  }

}


export class FormModel {
  searchText?: string;
  filter = 'searchMatchday';
}

export class IdToTeamName {
  id: number;
  teamName: string;
}
