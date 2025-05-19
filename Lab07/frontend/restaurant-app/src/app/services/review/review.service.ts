import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Review } from '../../models/review.model';

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

  addReview(review: Review): Observable<Review> {
    return this.httpClient.post<Review>(`${this.baseUrl}`, review)
  }

  updateReview(reviewId: number, review: Review): Observable<Review> {
    return this.httpClient.put<Review>(`${this.baseUrl}/${reviewId}`, review)
  }

  deleteReview(reviewId: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.baseUrl}/${reviewId}`);
  }
}
