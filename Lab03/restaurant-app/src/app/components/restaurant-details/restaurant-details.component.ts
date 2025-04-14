import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-restaurant-details',
  standalone: false,
  templateUrl: './restaurant-details.component.html',
  styleUrl: './restaurant-details.component.scss'
})
export class RestaurantDetailsComponent {
  @Input() restaurant!: any;

  checkIfHighMichelinStars(): boolean {
    return this.restaurant.michelinStars > 3;
  }
}
