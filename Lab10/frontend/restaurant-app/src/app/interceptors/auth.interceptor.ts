import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth/auth.service';
import { switchMap } from 'rxjs';
import { isTokenExpired } from '../utils/auth.utils';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const excludedEndpoints = ['/auth/refreshToken'];

  if (req.url.includes('/auth/login') && authService.isLoggedIn()) {
    return next(req.clone({ url: '/home' }));
  }

  if (excludedEndpoints.some(endpoint => req.url.includes(endpoint))) {
    return next(req);
  }

  const token = authService.getAccessToken();

  if (!token || isTokenExpired()) {
    const refreshToken = authService.getRefreshToken();
    if (!refreshToken) {
      return next(req);
    }

    return authService.refreshAccessToken().pipe(
      switchMap(() => {
        const updatedToken = authService.getAccessToken();
        const authReq = updatedToken
          ? req.clone({ setHeaders: { Authorization: `Bearer ${updatedToken}` } })
          : req;

        return next(authReq);
      })
    );
  }

  const authReq = token ? req.clone({
    setHeaders: { Authorization: `Bearer ${token}` }
  }) : req;

  return next(authReq);
}
