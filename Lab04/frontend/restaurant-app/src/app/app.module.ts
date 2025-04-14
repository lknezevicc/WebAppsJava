import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RestaurantListComponent } from './components/restaurant-list/restaurant-list.component';
import { RestaurantDetailsComponent } from './components/restaurant-details/restaurant-details.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { provideHttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RestaurantListMichelinFilterComponent } from './components/restaurant-list-michelin-filter/restaurant-list-michelin-filter.component';

@NgModule({
  declarations: [
    AppComponent,
    RestaurantListComponent,
    RestaurantDetailsComponent,
    NavbarComponent,
    RestaurantListMichelinFilterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [provideHttpClient()],
  bootstrap: [AppComponent]
})
export class AppModule { }
