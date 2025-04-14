import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RestaurantListComponent } from './components/restaurant-list/restaurant-list.component';
import { RestaurantDetailsComponent } from './components/restaurant-details/restaurant-details.component';
import { RestaurantListMichelinFilterComponent } from './components/restaurant-list-michelin-filter/restaurant-list-michelin-filter.component';

const routes: Routes = [
  { path: '', component: RestaurantListComponent},
  { path: 'restaurants/michelinStarsFilter', component: RestaurantListMichelinFilterComponent },
  { path: 'restaurants/:id', component: RestaurantDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
