import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-league-table',
  templateUrl: './league-table.component.html',
  styleUrls: ['./league-table.component.css']
})
export class LeagueTableComponent implements OnInit {

  constructor(private httpService: HttpService) { }

  ngOnInit() {
  }

  getLeagueTable() {
    this.httpService.getLeagueTable().subscribe(leagueTable => {
      console.log(leagueTable);
    });
  }
}
