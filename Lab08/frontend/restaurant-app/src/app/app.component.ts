import { Component, OnInit } from '@angular/core';
import { AuthService } from './services/auth/auth.service';
import { isTokenExpired } from './utils/auth.utils';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  title = 'restaurant-app';

  constructor(private authService: AuthService) {}

  ngOnInit() {
    setInterval(() => {
      const token = this.authService.getAccessToken();
      if (token && isTokenExpired()) {
        this.authService.logout();
        alert('Your session has expired. Please log in again.');
      }
    }, 10000);
  }
}
