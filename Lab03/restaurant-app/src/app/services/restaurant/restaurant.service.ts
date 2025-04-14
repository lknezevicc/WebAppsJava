import { Injectable } from '@angular/core';
import { Restaurant } from '../../models/restaurant.model';
import { DayOfWeek } from '../../enums/day-of-week.enum';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {
  private mockRestaurants: Restaurant[] = [
    {
      id: 1,
      name: 'Pizza House',
      address: 'Zagrebačka 10',
      phoneNumber: '099111222',
      email: 'pizza@house.com',
      workingHours: new Map([
        [DayOfWeek.MONDAY, '10:00 - 23:00'],
        [DayOfWeek.TUESDAY, '10:00 - 23:00'],
        [DayOfWeek.WEDNESDAY, '10:00 - 23:00'],
        [DayOfWeek.THURSDAY, '10:00 - 23:00'],
        [DayOfWeek.FRIDAY, '10:00 - 00:00'],
        [DayOfWeek.SATURDAY, '12:00 - 00:00'],
        [DayOfWeek.SUNDAY, '12:00 - 22:00']
      ]),
      description: 'Pizza and more',
      opened: true,
      averageDeliveryTime: 15.0,
      averageCustomerRating: 4.5,
      maxNumberOfOrders: 15,
      michelinStars: 4
    },
    {
      id: 2,
      name: 'Burger King',
      address: 'Ilica 23',
      phoneNumber: '099333444',
      email: 'burger@king.com',
      workingHours: new Map([
        [DayOfWeek.MONDAY, '09:00 - 22:00'],
        [DayOfWeek.TUESDAY, '09:00 - 22:00'],
        [DayOfWeek.WEDNESDAY, '09:00 - 22:00'],
        [DayOfWeek.THURSDAY, '09:00 - 22:00'],
        [DayOfWeek.FRIDAY, '09:00 - 23:00'],
        [DayOfWeek.SATURDAY, '11:00 - 23:00'],
        [DayOfWeek.SUNDAY, '11:00 - 21:00']
      ]),
      description: 'Fast food with a twist',
      opened: false,
      averageDeliveryTime: 25.0,
      averageCustomerRating: 4.2,
      maxNumberOfOrders: 40,
      michelinStars: 5
    },
    {
      id: 3,
      name: 'Sushi Bar',
      address: 'Savska 50',
      phoneNumber: '099555666',
      email: 'sushi@bar.com',
      workingHours: new Map([
        [DayOfWeek.MONDAY, '12:00 - 22:00'],
        [DayOfWeek.TUESDAY, '12:00 - 22:00'],
        [DayOfWeek.WEDNESDAY, '12:00 - 22:00'],
        [DayOfWeek.THURSDAY, '12:00 - 22:00'],
        [DayOfWeek.FRIDAY, '12:00 - 23:00'],
        [DayOfWeek.SATURDAY, '13:00 - 23:00'],
        [DayOfWeek.SUNDAY, '13:00 - 20:00']
      ]),
      description: 'Fresh and tasty sushi',
      opened: true,
      averageDeliveryTime: 40.0,
      averageCustomerRating: 4.8,
      maxNumberOfOrders: 30,
      michelinStars: 3
    },
    {
      id: 4,
      name: 'BBQ Joint',
      address: 'Vukovarska 15',
      phoneNumber: '099777888',
      email: 'bbq@joint.com',
      workingHours: new Map([
        [DayOfWeek.MONDAY, '11:00 - 22:00'],
        [DayOfWeek.TUESDAY, '11:00 - 22:00'],
        [DayOfWeek.WEDNESDAY, '11:00 - 22:00'],
        [DayOfWeek.THURSDAY, '11:00 - 22:00'],
        [DayOfWeek.FRIDAY, '11:00 - 23:00'],
        [DayOfWeek.SATURDAY, '12:00 - 23:00'],
        [DayOfWeek.SUNDAY, '12:00 - 21:00']
      ]),
      description: 'BBQ delights',
      opened: true,
      averageDeliveryTime: 35.0,
      averageCustomerRating: 4.6,
      maxNumberOfOrders: 45,
      michelinStars: 2
    },
    {
      id: 5,
      name: 'Healthy Bites',
      address: 'Gajeva 3',
      phoneNumber: '099999000',
      email: 'healthy@bites.com',
      workingHours: new Map([
        [DayOfWeek.MONDAY, '08:00 - 20:00'],
        [DayOfWeek.TUESDAY, '08:00 - 20:00'],
        [DayOfWeek.WEDNESDAY, '08:00 - 20:00'],
        [DayOfWeek.THURSDAY, '08:00 - 20:00'],
        [DayOfWeek.FRIDAY, '08:00 - 21:00'],
        [DayOfWeek.SATURDAY, '10:00 - 21:00'],
        [DayOfWeek.SUNDAY, '10:00 - 19:00']
      ]),
      description: 'Nutritious meals',
      opened: false,
      averageDeliveryTime: 20.0,
      averageCustomerRating: 4.9,
      maxNumberOfOrders: 25,
      michelinStars: 1
    }
  ];

  constructor() { }

  getRestaurants(): Observable<Restaurant[]> {
    return of(this.mockRestaurants);
  }
}
