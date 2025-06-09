import { Component, OnInit } from '@angular/core';
import { Restaurant } from '../../models/restaurant.model';
import { RestaurantService } from '../../services/restaurant/restaurant.service';

@Component({
  selector: 'app-top-cheapest-restaurants',
  standalone: false,
  templateUrl: './top-cheapest-restaurants.component.html',
  styleUrl: './top-cheapest-restaurants.component.scss'
})
export class TopCheapestRestaurantsComponent implements OnInit {
  cheapestRestaurants: Restaurant[] = [];

  constructor(private restaurantService: RestaurantService) { }

  ngOnInit(): void {
    this.fetchCheapestRestaurants();
  }

  fetchCheapestRestaurants(): void {
    this.restaurantService.getCheapestRestaurants().subscribe(
      {
        next: (restaurants: Restaurant[]) => {
          this.cheapestRestaurants = restaurants;
        },
        error: (error) => {
          console.error('Error fetching cheapest restaurants:', error);
        }
      }
    );
  }

}
