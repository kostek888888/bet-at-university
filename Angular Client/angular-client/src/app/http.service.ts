import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { LeagueTableModel } from './models/leagueTableModel';
import { MatchModel } from './models/matchModel';
import { environment } from '../environments/environment';
import { NgForm } from '@angular/forms';



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

  postLogin(login_param: string, password_param: string): Observable<boolean> {
    const params = new HttpParams()
      .append('login', login_param)
      .append('password', password_param);
    return this.http.post<boolean>( this.baseURl + '/logIn', params, { withCredentials: true} );
  }

  postLogout(): Observable<boolean> {
    return this.http.post<boolean>(this.baseURl + '/logOut', null, { withCredentials: true });
  }

  postRegister(formData: NgForm): Observable<boolean> {
    const params = new HttpParams()
      .append('login', formData.value.login)
      .append('password', formData.value.password)
      .append('name', formData.value.name)
      .append('surname', formData.value.surname)
      .append('birthDate', formData.value.birthDate)
      .append('address', formData.value.address)
      .append('postCode', formData.value.postCode);
    return this.http.post<boolean>(this.baseURl + '/register', params);
  }

  checkLoginAvailability(userLogin: string): Observable<boolean> {
    const param = new HttpParams()
      .append('login', userLogin);
    return this.http.post<boolean>(this.baseURl + '/checkUsers', param);
  }
}
