import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { LeagueTableModel } from './models/leagueTableModel';
import { MatchModel } from './models/matchModel';
import { environment } from '../environments/environment';



@Injectable()
export class HttpService {
  private server_link = environment.config.server_link;
  constructor(private http: HttpClient) { }

  getLeagueTable(): Observable<LeagueTableModel> {
    console.log(this.server_link + 'team');
    return this.http.get<LeagueTableModel>(this.server_link + 'team');
  }

  getMatch(): Observable<MatchModel> {
    console.log(this.server_link + 'match');
    return this.http.get<MatchModel>(this.server_link + 'match');
  }
}
