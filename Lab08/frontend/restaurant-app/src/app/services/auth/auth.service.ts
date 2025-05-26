import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { map, Observable, tap } from 'rxjs';
import { Jwt } from '../../models/jwt.model';
import { Router } from '@angular/router';
import { hasRole } from '../../utils/auth.utils';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly baseUrl = environment.apiUrl + 'auth';
  private loggedIn: boolean = false;

  constructor(
    private httpClient: HttpClient,
    private router: Router
  ) { }

  login(username: string, password: string) {
    return this.httpClient.post<Jwt>(`${this.baseUrl}/login`, { username, password }).pipe(
      tap(response => {
        localStorage.setItem('accessToken', response.accessToken);
        localStorage.setItem('refreshToken', response.refreshToken);
        this.loggedIn = true;
      })
    )
  }

  logout() {
    const refreshToken = this.getRefreshToken();
    this.httpClient.post(`${this.baseUrl}/logout`, { token: refreshToken }).subscribe({
      next: () => {
        localStorage.removeItem('accessToken');
        localStorage.removeItem('refreshToken');
        this.loggedIn = false;
        this.router.navigate(['/login']);
      }
    });
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('accessToken');
  }

  getAccessToken(): string | null {
    return localStorage.getItem('accessToken');
  }

  getRefreshToken(): string | null {
    return localStorage.getItem('refreshToken');
  }

  refreshAccessToken(): Observable<string> {
    return this.httpClient.post<Jwt>(`${this.baseUrl}/refreshToken`, { token: this.getRefreshToken() })
      .pipe(
        tap(response => {
          localStorage.setItem('accessToken', response.accessToken);
        }),
        map(response => response.accessToken)
      );
  }

  hasThisRole(role: string): boolean {
    return hasRole(role);
  }

}
