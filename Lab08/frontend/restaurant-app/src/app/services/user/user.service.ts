import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly baseUrl = environment.apiUrl + 'users';

  constructor(private httpClient: HttpClient) { }

  getCurrentUser(): Observable<User> {
    return this.httpClient.get<User>(`${this.baseUrl}/me`);
  }
}
