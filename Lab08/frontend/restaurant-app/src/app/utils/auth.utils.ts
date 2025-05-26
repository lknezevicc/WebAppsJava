import { jwtDecode } from 'jwt-decode';
import { AuthService } from '../services/auth/auth.service';
import { inject } from '@angular/core';

interface JwtPayload {
  sub?: string;
  username?: string;
  roles?: string[];
  exp?: number;
  [key: string]: any;
}

export function getDecodedToken(): JwtPayload | null {
  const authService = inject(AuthService);
  const token = authService.getAccessToken();
  if (!token) return null;

  try {
    return jwtDecode<JwtPayload>(token);
  } catch (e) {
    console.error('Invalid token', e);
    return null;
  }
}

export function getUsername(): string | null {
  const decoded = getDecodedToken();
  return decoded?.username || decoded?.sub || null;
}

export function hasRole(role: string): boolean {
  const decoded = getDecodedToken();
  console.log('Decoded token:', decoded);
  return decoded?.roles?.includes(role) || false;
}

export function isTokenExpired(): boolean {
  const decoded = getDecodedToken();
  if (!decoded || !decoded.exp) return true;

  const currentTime = Math.floor(Date.now() / 1000);
  return decoded.exp < currentTime;
}