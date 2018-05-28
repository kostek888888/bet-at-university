import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { LeagueTableModel } from './models/leagueTableModel';
import { MatchModel } from './models/matchModel';
import { LoginModel } from './models/userModel';



@Injectable()
export class HttpService {

  constructor(private http: HttpClient) { }

  getLeagueTable(): Observable<LeagueTableModel> {
    return this.http.get<LeagueTableModel>('http://localhost:8080/team');
  }

  getMatch(): Observable<MatchModel> {
    return this.http.get<MatchModel>('http://localhost:8080/match');
  }

  postLogin(login_param: string, password_param: string): Observable<LoginModel> {
    const params = new HttpParams()
      .append('login', login_param)
      .append('password', password_param);

    return this.http.post<LoginModel>('http://localhost:8080/logIn', null, {params: params, withCredentials: true} );
  }

  postLogout() {
    this.http.post('http://localhost:8080/logout', {});
  }
}
