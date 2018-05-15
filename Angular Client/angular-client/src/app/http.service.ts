import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { LeagueTableModel } from './models/leagueTableModel';

@Injectable()
export class HttpService {

  apiKeyHeader = new HttpHeaders().set('X-Auth-Token', '5407750046da4af5a387714fba7ea516');

  constructor(private http: HttpClient) { }

  getLeagueTable(): Observable<LeagueTableModel> {
    return this.http.get<LeagueTableModel>('http://api.football-data.org/v1/competitions/445/leagueTable', {headers: this.apiKeyHeader});
  }
}
