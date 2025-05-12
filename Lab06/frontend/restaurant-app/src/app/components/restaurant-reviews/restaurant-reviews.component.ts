import { Component, Input, OnInit } from '@angular/core';
import { ReviewService } from '../../services/review/review.service';
import { Review } from '../../models/review.model';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-restaurant-reviews',
  standalone: false,
  templateUrl: './restaurant-reviews.component.html',
  styleUrl: './restaurant-reviews.component.scss'
})
export class RestaurantReviewsComponent implements OnInit {
  @Input() restaurantId!: number;
  reviews: Review[] = [];
  review: Review = { id: 0, title: '', description: '', rating: 1 };

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

  onSubmitReview(form: NgForm): void {
    if (form.valid) {
      this.reviewService.addReview(this.restaurantId, this.review).subscribe({
        next: (data) => {
          this.reviews.push(data);
          form.reset();
        },
        error: (error) => {
          console.error('Error adding review:', error);
        }
      });
    }
  }
}
