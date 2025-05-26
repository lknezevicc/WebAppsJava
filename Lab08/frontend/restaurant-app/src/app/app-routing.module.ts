import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RestaurantListComponent } from './components/restaurant-list/restaurant-list.component';
import { RestaurantDetailsComponent } from './components/restaurant-details/restaurant-details.component';
import { authGuard } from './guards/auth/auth.guard';
import { LoginComponent } from './components/login/login.component';
import { CurrentUserComponent } from './components/current-user/current-user.component';

const routes: Routes = [
  { path: '', component: RestaurantListComponent, canActivate: [authGuard] },
  { path: 'restaurants/:id', component: RestaurantDetailsComponent, canActivate: [authGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'current-user', component: CurrentUserComponent, canActivate: [authGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
