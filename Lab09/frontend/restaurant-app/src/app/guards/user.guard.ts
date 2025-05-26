import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';
import { inject } from '@angular/core';

export const userGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  const expectedRole = route.data['role'];

  if (!authService.isLoggedIn()) {
    router.navigate(['/login']);
    return false;
  }

  if (!authService.hasThisRole(expectedRole)) {
    router.navigate(['/unauthorized']);
    return false;
  }

  return true;
};
