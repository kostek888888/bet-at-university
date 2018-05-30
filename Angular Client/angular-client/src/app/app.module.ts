import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app.routing.module';
import { AuthGuardService } from './auth/auth-guard.service';
import { AuthService } from './auth/auth.service';
import { HttpService } from './http.service';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { GamesResultsComponent } from './games-results/games-results.component';
import { YourProfileComponent } from './your-profile/your-profile.component';
import { HomeComponent } from './home/home.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { LoginComponent } from './auth/login/login.component';
import { LeagueTableComponent } from './league-table/league-table.component';
import { RegisterComponent } from './register/register.component';



@NgModule({
  declarations: [
    AppComponent,
    GamesResultsComponent,
    YourProfileComponent,
    HomeComponent,
    PageNotFoundComponent,
    LoginComponent,
    LeagueTableComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [AuthGuardService, AuthService, HttpService, CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
