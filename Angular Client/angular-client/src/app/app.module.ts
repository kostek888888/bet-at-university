import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app.routing.module';
import { AuthGuardService } from './auth/auth-guard.service';
import { AuthService } from './auth/auth.service';
import { HttpService } from './http.service';
import { HttpClientModule } from '@angular/common/http';
import { AngularFireModule } from 'angularfire2';
import { AngularFireAuthModule } from 'angularfire2/auth';

import { AppComponent } from './app.component';
import { GamesResultsComponent } from './games-results/games-results.component';
import { YourProfileComponent } from './your-profile/your-profile.component';
import { HomeComponent } from './home/home.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { LoginComponent } from './auth/login/login.component';
import { LeagueTableComponent } from './league-table/league-table.component';
import { RegisterComponent } from './register/register.component';
import { BetsHistoryComponent } from './your-profile/bets-history/bets-history.component';
import { PlaceABetComponent } from './place-a-bet/place-a-bet.component';
import { NextMatchesComponent } from './next-matches/next-matches.component';
import { TranslateTeamsNamesPipe } from './models/translate-teams-names.pipe';

const config = {
  apiKey: 'AIzaSyBJLTQwVwvABt0VYLpO3sIk_cdKkpxMs_k',
  authDomain: 'bet-at-university.firebaseapp.com',
  databaseURL: 'https://bet-at-university.firebaseio.com',
  projectId: 'bet-at-university',
  storageBucket: 'bet-at-university.appspot.com',
  messagingSenderId: '612444667505'
};



@NgModule({
  declarations: [
    AppComponent,
    GamesResultsComponent,
    YourProfileComponent,
    HomeComponent,
    PageNotFoundComponent,
    LoginComponent,
    LeagueTableComponent,
    RegisterComponent,
    BetsHistoryComponent,
    PlaceABetComponent,
    NextMatchesComponent,
    TranslateTeamsNamesPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    AngularFireModule.initializeApp(config),
    AngularFireAuthModule
  ],
  providers: [
    AuthGuardService, AuthService, HttpService, CookieService
    ],
  bootstrap: [AppComponent]
})
export class AppModule { }
