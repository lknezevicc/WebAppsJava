import { Injectable } from '@angular/core';
import { Restaurant } from '../../models/restaurant.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {
  private readonly baseUrl = environment.apiUrl + 'restaurants';

  constructor(private httpClient: HttpClient) { }

  getRestaurants(): Observable<Restaurant[]> {
    return this.httpClient.get<Restaurant[]>(this.baseUrl);
  }

  getRestaurantById(id: number): Observable<Restaurant> {
    return this.httpClient.get<Restaurant>(`${this.baseUrl}/${id}`);
  }

  addRestaurant(restaurant: Restaurant): Observable<Restaurant> {
    return this.httpClient.post<Restaurant>(this.baseUrl, restaurant);
  }

  updateRestaurant(restaurantId: number, restaurant: Restaurant): Observable<Restaurant> {
    return this.httpClient.put<Restaurant>(`${this.baseUrl}/${restaurantId}`, restaurant);
  }

  deleteRestaurant(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.baseUrl}/${id}`);
  }

  getRestaurantsByMichelinStar(star: number): Observable<Restaurant[]> {
    return this.httpClient.get<Restaurant[]>(`${this.baseUrl}/michelinStar/${star}`);
  }
}
