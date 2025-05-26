import { Component, Input, OnInit } from '@angular/core';
import { ReviewService } from '../../services/review/review.service';
import { Review } from '../../models/review.model';
import { getUsername, hasRole } from '../../utils/auth.utils';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-restaurant-reviews',
  standalone: false,
  templateUrl: './restaurant-reviews.component.html',
  styleUrl: './restaurant-reviews.component.scss'
})
export class RestaurantReviewsComponent implements OnInit {
  @Input() restaurantId!: number;

  reviews: Review[] = [];
  reviewForm!: FormGroup;
  editingReview: Review | null = null;

  currentUser = getUsername();
  isAdmin = hasRole('ROLE_ADMIN');
  ownReviewExists = false;

  constructor(
    private reviewService: ReviewService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.reviewForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      rating: [1, [Validators.required, Validators.min(1), Validators.max(5)]]
    });

    this.loadReviews();
  }

  loadReviews(): void {
    this.reviewService.getReviewsByRestaurantId(this.restaurantId).subscribe({
      next: (data) => {
        this.reviews = data;
        this.ownReviewExists = this.reviews.some(r => r.username === this.currentUser);
      }
    });
  }

  get showAddForm(): boolean {
    return !this.isAdmin && !this.ownReviewExists && !this.editingReview;
  }

  canEditReview(review: Review): boolean {
    return review.username === this.currentUser || this.isAdmin;
  }

  startEdit(review: Review): void {
    this.editingReview = review;
    this.reviewForm.patchValue({
      title: review.title,
      description: review.description,
      rating: review.rating
    });
  }

  cancelEdit(): void {
    this.editingReview = null;
    this.reviewForm.reset({ title: '', description: '', rating: 1 });
  }

  onSubmit(): void {
    if (this.reviewForm.invalid) return;
    if (this.isAdmin && !this.editingReview) return;

    const payload = {
      ...this.reviewForm.value,
      restaurantId: this.restaurantId
    };

    if (this.editingReview) {
      this.reviewService.updateReview(this.editingReview.id, payload).subscribe({
        next:() => {
          this.cancelEdit();
          this.loadReviews();
        }
      });
    } else {
      this.reviewService.addReview(payload).subscribe({
        next: () => {
          this.reviewForm.reset({ title: '', description: '', rating: 1 });
          this.loadReviews();
        }
      });
    }
  }

  deleteReview(reviewId: number): void {
  if (!this.isAdmin) return;

  this.reviewService.deleteReview(reviewId).subscribe({
    next: () => this.loadReviews(),
    error: (err) => console.error('Delete failed', err)
  });
}
}
