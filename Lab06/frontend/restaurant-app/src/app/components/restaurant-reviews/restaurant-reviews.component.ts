import { Component, Input, OnInit } from '@angular/core';
import { ReviewService } from '../../services/review/review.service';
import { Review } from '../../models/review.model';

@Component({
  selector: 'app-restaurant-reviews',
  standalone: false,
  templateUrl: './restaurant-reviews.component.html',
  styleUrl: './restaurant-reviews.component.scss'
})
export class RestaurantReviewsComponent implements OnInit {
  @Input() restaurantId!: number;
  reviews: Review[] = [];

  constructor(private reviewService: ReviewService) {}

  ngOnInit(): void {
    this.getReviewsByRestaurantId(this.restaurantId);
  }

  getReviewsByRestaurantId(restaurantId: number): void {
    this.reviewService.getReviewsByRestaurantId(restaurantId).subscribe({
      next: (reviews) => {
        this.reviews = reviews;
      },
      error: (error) => {
        console.error('Error fetching reviews:', error);
      }
    });
  }
}
