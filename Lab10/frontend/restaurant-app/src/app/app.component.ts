import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.scss'
})
export class AppComponent {
  constructor(private translateService: TranslateService) {
    this.translateService.setDefaultLang('en');
    this.translateService.use('en');
  }

  title = 'restaurant-app';

  switchLanguage(lang: string) {
    this.translateService.use(lang);
  }
}
