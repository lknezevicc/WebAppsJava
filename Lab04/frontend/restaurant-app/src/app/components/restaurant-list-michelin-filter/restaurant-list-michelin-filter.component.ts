import { Component, OnInit } from '@angular/core';
import { Restaurant } from '../../models/restaurant.model';
import { RestaurantService } from '../../services/restaurant/restaurant.service';

@Component({
  selector: 'app-restaurant-list-michelin-filter',
  standalone: false,
  templateUrl: './restaurant-list-michelin-filter.component.html',
  styleUrl: './restaurant-list-michelin-filter.component.scss'
})
export class RestaurantListMichelinFilterComponent implements OnInit {
  michelinStarOptions = [0, 1, 2, 3];
  selectedStars: number = 0;
  restaurants: Restaurant[] = [];

  constructor(private restaurantService: RestaurantService) {}

  ngOnInit(): void {
    this.getRestaurants(this.selectedStars);
  }

  getRestaurants(selectedStars: number): void {
    this.restaurantService.getRestaurantsByMichelinStar(selectedStars).subscribe({
      next: (restaurants) => {
        this.restaurants = restaurants;
        console.log('Restaurants fetched successfully:', this.restaurants);
      },
      error: (error) => {
        console.error('Error fetching restaurants:', error);
      }
    });
  }

  getCustomerRatingClass(rating: number): string {
    return rating > 4 ? 'good' 
      : rating > 2.5 ? 'mid' 
      : 'bad';
  }

  getDeliveryTimeRatingClass(deliveryTime: number): string {
    return deliveryTime <= 20 ? 'good' 
      : deliveryTime <= 35 ? 'mid' 
      : 'bad';
  }

}
