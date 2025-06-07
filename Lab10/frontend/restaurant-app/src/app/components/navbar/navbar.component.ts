import { Component } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-navbar',
  standalone: false,
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {
  constructor(
    private authService: AuthService,
    private translateService: TranslateService
  ) { }

  logout() {
    this.authService.logout();
  }

  isLoggedIn() {
    return this.authService.isLoggedIn();
  }

  switchLanguage(lang: string) {
    this.translateService.use(lang);
  }

  getCurrentLanguage() {
    return this.translateService.currentLang || 'en';
  }
}
