import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { YourProfileComponent } from './your-profile/your-profile.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { HomeComponent } from './home/home.component';
import { GamesResultsComponent } from './games-results/games-results.component';
import { AuthGuardService } from './auth/auth-guard.service';
import { LoginComponent } from './auth/login/login.component';
import { LeagueTableComponent } from './league-table/league-table.component';
import { RegisterComponent } from './register/register.component';
import { BetsHistoryComponent } from './your-profile/bets-history/bets-history.component';
import { PlaceABetComponent } from './place-a-bet/place-a-bet.component';

const appRoutes: Routes = [
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'your-profile',
    component: YourProfileComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'your-profile/bets-history',
    component: BetsHistoryComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'place-a-bet',
    component: PlaceABetComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'games-results',
    component: GamesResultsComponent
  },
  {
    path: 'league-table',
    component: LeagueTableComponent
  },
  {
    path: '**',
    component: PageNotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes, { onSameUrlNavigation: 'reload' })],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
