import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { LeagueTableModel } from './models/leagueTableModel';
import { MatchModel } from './models/matchModel';
import { environment } from '../environments/environment';
import { NgForm } from '@angular/forms';
import { UserStatisticModel, User } from './models/userModel';
import { BetsRatesModel } from './models/betsRatesModel';
import { AuthService } from './auth/auth.service';
import { BetModel } from './models/betModel';



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

  getMatchToBetHttpService(): Observable<MatchModel> {
    return this.http.get<MatchModel>(this.baseURl + '/matchToBet');
  }

  getBetsRatesHttpService(): Observable<BetsRatesModel> {
    return this.http.get<BetsRatesModel>(this.baseURl + '/betRate');
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

  postUserStatisticFromId(userId: string): Observable<UserStatisticModel> {
    const param = new HttpParams().append('id', userId);
    return this.http.post<UserStatisticModel>(this.baseURl + '/userStatisticsFromId', param);
  }

  postIncreaseAccountBalance(userId: string, money: string): Observable<UserStatisticModel> {
    const params = new HttpParams().append('userId', userId)
                                   .append('money', money);
    return this.http.post<UserStatisticModel>(this.baseURl + '/pay', params);
  }

  postBetMatch(matchId: string, userId: string, betType: string, moneyInserted: string): Observable<boolean> {
    const params = new HttpParams()
      .append('matchId', matchId)
      .append('userId', userId)
      .append('betType', betType)
      .append('moneyInserted', moneyInserted);
    return this.http.post<boolean>(this.baseURl + '/betMatch', params);
  }

  postActiveBets(userId: string): Observable<BetModel> {
    const params = new HttpParams().append('userId', userId);
    return this.http.post<BetModel>(this.baseURl + '/userBet', params);
  }
}
