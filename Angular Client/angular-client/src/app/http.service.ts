import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { LeagueTableModel } from './models/leagueTableModel';
import { MatchModel } from './models/matchModel';
import { environment } from '../environments/environment';



@Injectable()
export class HttpService {

  private baseURl = environment.config.baseURL;
  constructor(private http: HttpClient) { }

  getLeagueTable(): Observable<LeagueTableModel> {
    return this.http.get<LeagueTableModel>( this.baseURl + '/team');
  }

  getMatch(): Observable<MatchModel> {
    return this.http.get<MatchModel>( this.baseURl + '/match');
  }

  postLogin(login_param: string, password_param: string): Observable<number> {
    const params = new HttpParams()
      .append('login', login_param)
      .append('password', password_param);
    return this.http.post<number>( this.baseURl + '/logIn', params, { withCredentials: true} );
  }

  postLogout() {
    this.http.post(this.baseURl +  '/logout', {});
  }
}
