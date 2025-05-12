import { Component, OnInit } from '@angular/core';
import { Restaurant } from '../../models/restaurant.model';
import { RestaurantService } from '../../services/restaurant/restaurant.service';
import { NgForm } from '@angular/forms';
import { DayOfWeek } from '../../enums/day-of-week.enum';
import { WorkingHour } from '../../models/workingHour.model';

@Component({
  selector: 'app-restaurant-list',
  standalone: false,
  templateUrl: './restaurant-list.component.html',
  styleUrls: ['./restaurant-list.component.scss']
})
export class RestaurantListComponent implements OnInit {
  restaurants: Restaurant[] = [];
  daysOfWeek = Object.values(DayOfWeek);
  workingHours: WorkingHour[] = [];
  formModel: any = {};

  constructor(private restaurantService: RestaurantService) {}

  ngOnInit(): void {
    this.getRestaurants();
  }

  getWorkloadPercentageLabel(workloadPercentage: number | undefined): string {
    if (workloadPercentage === undefined) return 'N/A';
    return workloadPercentage > 80 ? 'high' :
      workloadPercentage > 50 ? 'medium' : 'low';
  }

  onSubmit(formRef: NgForm): void {
    this.formModel.opened = formRef.value.opened === 'true';
    const restaurantData: Restaurant = {
      ...this.formModel,
      workingHours: this.workingHours
    };

    this.addRestaurant(restaurantData);

    formRef.resetForm();
    this.workingHours = [];
    this.formModel = {};
  }

  getRestaurants(): void {
    this.restaurantService.getRestaurants().subscribe({
      next: (restaurants) => {
        this.restaurants = restaurants;
      },
      error: (error) => {
        console.error('Error fetching restaurants:', error);
      }
    });
  }

  addRestaurant(data: Restaurant): void {
    this.restaurantService.addRestaurant(data).subscribe({
      next: () => {
        this.getRestaurants();
        console.log('Restaurant added successfully!');
      },
      error: (err) => console.error('Error adding restaurant:', err)
    });
  }

  onDelete(restaurantId: number): void {
    this.restaurantService.deleteRestaurant(restaurantId).subscribe({
      next: () => {
        this.getRestaurants();
        console.log('Restaurant deleted successfully!');
      },
      error: (error) => console.error('Error deleting restaurant:', error)
    });
  }

  getWorkingHourForDay(day: DayOfWeek): WorkingHour {
    let entry = this.workingHours.find(e => e.dayOfWeek === day);
    if (!entry) {
      entry = { dayOfWeek: day, workingHour: '' };
      this.workingHours.push(entry);
    }
    return entry;
  }
}