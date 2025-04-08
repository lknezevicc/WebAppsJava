import { Component, Input } from '@angular/core';
import { Restaurant } from '../../models/restaurant.model';

@Component({
  selector: 'app-restaurant-details',
  standalone: false,
  templateUrl: './restaurant-details.component.html',
  styleUrl: './restaurant-details.component.scss'
})
export class RestaurantDetailsComponent {
  @Input() restaurant!: Restaurant;
}
