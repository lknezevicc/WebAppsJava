import { Component, OnInit } from '@angular/core';
import { Restaurant } from '../../models/restaurant.model';
import { ActivatedRoute } from '@angular/router';
import { RestaurantService } from '../../services/restaurant/restaurant.service';
import { NgForm } from '@angular/forms';
import { DayOfWeek } from '../../enums/day-of-week.enum';
import { WorkingHour } from '../../models/workingHour.model';

@Component({
  selector: 'app-restaurant-details',
  standalone: false,
  templateUrl: './restaurant-details.component.html',
  styleUrl: './restaurant-details.component.scss'
})
export class RestaurantDetailsComponent implements OnInit {
  restaurantId!: number;
  restaurant!: Restaurant;
  daysOfWeek = Object.values(DayOfWeek);

  constructor(
    private route: ActivatedRoute, 
    private restaurantService: RestaurantService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.restaurantId = id ? +id : 0;
    this.getRestaurant(this.restaurantId);
  }

  onUpdate(formRef: NgForm): void {
    if (formRef.valid) {
      this.updateRestaurant(this.restaurantId, this.restaurant);
      formRef.resetForm();
    }
  }

  getRestaurant(id: number): void {
    this.restaurantService.getRestaurantById(id).subscribe({
      next: (restaurant) => {
        this.restaurant = restaurant;
      },
      error: (error) => {
        console.error('Error fetching restaurant details:', error);
      }
    });
  }

  updateRestaurant(id: number, data: Restaurant): void {
    this.restaurantService.updateRestaurant(id, data).subscribe({
      next: (data) => {
        this.restaurant = data;
        console.log('Restaurant updated successfully!');
      },
      error: (err) => {
        console.error('Error updating restaurant:', err);
      }
    });
  }

  getWorkingHourForDay(day: DayOfWeek): WorkingHour {
    const existing = this.restaurant.workingHours.find(wh => wh.dayOfWeek === day);
    if (existing) return existing;

    const newEntry =  { dayOfWeek: day, workingHour: '' };
    this.restaurant.workingHours.push(newEntry);
    return newEntry;
  }
  
}
