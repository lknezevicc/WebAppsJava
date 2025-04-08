import { Component, OnInit } from '@angular/core';
import { Restaurant } from '../../models/restaurant.model';
import { RestaurantService } from '../../services/restaurant/restaurant.service';
import { RestaurantNEW } from '../../models/restaurantNEW.model';

@Component({
  selector: 'app-restaurant-list',
  standalone: false,
  templateUrl: './restaurant-list.component.html',
  styleUrl: './restaurant-list.component.scss'
})
export class RestaurantListComponent implements OnInit {
  restaurants: Restaurant[] = [];
  selectedRestaurant!: Restaurant | RestaurantNEW;

  sortKey: 'name' | 'opened' | 'load' = 'name';
  sortDirection: 'asc' | 'desc' = 'asc';
  filterText: string = '';


  constructor(private restaurantService: RestaurantService) {}

  ngOnInit(): void {
    this.restaurantService.getRestaurants().subscribe(data => {
      this.restaurants = data;
    })
  }

  selectRestaurant(restaurant: Restaurant): void {
    if (restaurant.michelinStars > 3) {
      this.selectedRestaurant = {
        id: restaurant.id,
        name: restaurant.name,
        address: restaurant.address,
        phoneNumber: restaurant.phoneNumber,
        email: restaurant.email,
        opened: restaurant.opened,
        michelinStars: restaurant.michelinStars,
      };
    } else {
      this.selectedRestaurant = restaurant;
    }
  }

  filteredAndSortedRestaurants(): Restaurant[] {
    return this.restaurants
      .filter(restaurant =>
        restaurant.name.toLowerCase().includes(this.filterText.toLowerCase())
      )
      .sort((a, b) => {
        let compareValue = 0;
  
        if (this.sortKey === 'name') {
          compareValue = a.name.localeCompare(b.name);
        } else if (this.sortKey === 'opened') {
          compareValue = Number(a.opened) - Number(b.opened);
        } else if (this.sortKey === 'load') {
          compareValue = a.maxNumberOfOrders - b.maxNumberOfOrders;
        }
  
        return this.sortDirection === 'asc' ? compareValue : -compareValue;
      });
  }

  setSort(key: 'name' | 'opened' | 'load'): void {
    if (this.sortKey === key) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortKey = key;
      this.sortDirection = 'asc';
    }
  }

}
