import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Review } from '../../models/review.model';
import { Restaurant } from '../../models/restaurant.model';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  private readonly baseUrl = environment.apiUrl + 'reviews';

  constructor(private httpClient: HttpClient) { }

  getReviews(): Observable<Review[]> {
    return this.httpClient.get<Review[]>(this.baseUrl);
  }

  getReviewsByRestaurantId(restaurantId: number): Observable<Review[]> {
    return this.httpClient.get<Review[]>(`${this.baseUrl}/restaurant/${restaurantId}`);
  }

  addReview(restaurantId: number, review: Review): Observable<Review> {
    return this.httpClient.post<Review>(`${this.baseUrl}/${restaurantId}`, review);
  }

  getTopRatedRestaurantLastMonth(): Observable<Restaurant> {
    return this.httpClient.get<Restaurant>(`${this.baseUrl}/restaurant/top-rated-last-month`);
  }

  getTopRatedRestaurantLastWeek(): Observable<Restaurant> {
    return this.httpClient.get<Restaurant>(`${this.baseUrl}/restaurant/top-rated-last-week`);
  }

}
