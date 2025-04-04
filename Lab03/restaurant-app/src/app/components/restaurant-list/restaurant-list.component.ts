import { Component, OnInit } from '@angular/core';
import { Restaurant } from '../../models/restaurant.model';
import { RestaurantService } from '../../services/restaurant/restaurant.service';

@Component({
  selector: 'app-restaurant-list',
  standalone: false,
  templateUrl: './restaurant-list.component.html',
  styleUrl: './restaurant-list.component.scss'
})
export class RestaurantListComponent implements OnInit {
  restaurants: Restaurant[] = [];
  selectedRestaurant: Restaurant | null = null;

  constructor(private restaurantService: RestaurantService) {}

  ngOnInit(): void {
    this.restaurantService.getRestaurants().subscribe(data => {
      this.restaurants = data;
    })
  }

  selectRestaurant(restaurant: Restaurant): void {
    this.selectedRestaurant = restaurant;
  }

}
