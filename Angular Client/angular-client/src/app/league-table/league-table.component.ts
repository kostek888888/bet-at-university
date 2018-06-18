import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service';
import { LeagueTableModel, Convert } from '../models/leagueTableModel';

@Component({
  selector: 'app-league-table',
  templateUrl: './league-table.component.html',
  styleUrls: ['./league-table.component.css']
})
export class LeagueTableComponent implements OnInit {

  constructor(private httpService: HttpService) { }

   standingsTable: LeagueTableModel;

  ngOnInit() {
    this.getLeagueTable();
  }

  getLeagueTable() {
    this.httpService.getLeagueTable().subscribe(leagueTable => {
      this.standingsTable = Convert.toLeagueTableModel(JSON.stringify(leagueTable));
    });
  }
}
