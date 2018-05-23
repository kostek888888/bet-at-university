import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { LeagueTableModel } from './models/leagueTableModel';
import { MatchModel } from './models/matchModel';



@Injectable()
export class HttpService {

  constructor(private http: HttpClient) { }

  getLeagueTable(): Observable<LeagueTableModel> {
    return this.http.get<LeagueTableModel>('http://localhost:8080/team');
  }

  getMatch(): Observable<MatchModel> {
    return this.http.get<MatchModel>('http://localhost:8080/match');
  }
}
