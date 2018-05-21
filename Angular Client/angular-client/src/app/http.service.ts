import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { LeagueTableModel } from './models/leagueTableModel';
import { FixturesArray } from './models/fixturesModel';


const competitionId = 445; // id dla ligi angielskiej

@Injectable()
export class HttpService {

  apiKeyHeader = new HttpHeaders().set('X-Auth-Token', '5407750046da4af5a387714fba7ea516');
  constructor(private http: HttpClient) { }

  getLeagueTable(): Observable<LeagueTableModel> {
    return this.http.get<LeagueTableModel>('http://api.football-data.org/v1/competitions/' +
      competitionId + '/leagueTable', { headers: this.apiKeyHeader });
  }

  getFixtures() {
    return this.http.get('http://api.football-data.org/v1/competitions/' +
      competitionId + '/fixtures', { headers: this.apiKeyHeader });
  }

  getFixturesByMatchday(matchday: string): Observable<FixturesArray> {
    return this.http.get<FixturesArray>('http://api.football-data.org/v1/competitions/' +
      competitionId + '/fixtures?matchday=' + matchday, { headers: this.apiKeyHeader });
  }

  // pobierz mecze druzyny (teamid) na nastepne dni (days) domyslnie 7
  getNextFixturesByTeam(teamId: string, nextDays: string = '7'): Observable<FixturesArray> {
    return this.http.get<FixturesArray>('http://api.football-data.org/v1/teams/' + teamId + '/fixtures?timeFrame=n' +
     nextDays, { headers: this.apiKeyHeader });
  }

  // to co wyzej tylko poprzednie dni
  getPastFixturesByTeam(teamId: string, pastDays: string = '7'): Observable<FixturesArray> {
    return this.http.get<FixturesArray>('http://api.football-data.org/v1/teams/' + teamId + '/fixtures?timeFrame=p' +
      pastDays, { headers: this.apiKeyHeader });
  }
}
