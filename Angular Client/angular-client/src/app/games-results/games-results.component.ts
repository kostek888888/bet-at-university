import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-games-results',
  templateUrl: './games-results.component.html',
  styleUrls: ['./games-results.component.css']
})
export class GamesResultsComponent implements OnInit {

  gamesResults = null;
  formObj = new FormModel();
  constructor(private httpService: HttpService) { }

  ngOnInit() {
  }

  getSearchResult() {
    if (this.formObj.filter === 'searchMatchday') {
      this.httpService.getFixturesByMatchday(this.formObj.searchText).subscribe(response => {
        this.gamesResults = JSON.stringify(response);
        console.log(response);
      });
    } else {
      // pobierz mecze dla druzyny 3 dni przed i 3 dni wprzod
      this.httpService.getPastFixturesByTeam(this.formObj.searchText, '3').subscribe(response => {
        this.gamesResults = JSON.stringify(response);
        console.log(response);
      });

      this.httpService.getNextFixturesByTeam(this.formObj.searchText, '3').subscribe(response => {
        this.gamesResults += JSON.stringify(response);
        console.log(response);
      });
    }
  }

}


export class FormModel {
  searchText?: string;
  filter = 'searchMatchday';
}
