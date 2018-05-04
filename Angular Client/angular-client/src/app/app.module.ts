import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AngularFireModule } from 'angularfire2';
import { AngularFireAuthModule } from 'angularfire2/auth';

import { AppComponent } from './app.component';
import { GamesResultsComponent } from './games-results/games-results.component';
import { YourProfileComponent } from './your-profile/your-profile.component';
import { HomeComponent } from './home/home.component';
import { AppRoutingModule } from './app.routing.module';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AuthGuardService } from './auth/auth-guard.service';
import { AuthService } from './auth/auth.service';
import { LoginComponent } from './auth/login/login.component';
import { FormsModule } from '@angular/forms';


const firebaseConfig = {
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
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    AngularFireModule.initializeApp(firebaseConfig),
    AngularFireAuthModule
  ],
  providers: [AuthGuardService, AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
