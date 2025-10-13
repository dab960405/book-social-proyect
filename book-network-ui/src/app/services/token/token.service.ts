import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  set token(token: string) {
    localStorage.setItem('token', token);
  }

  get token() {
    return localStorage.getItem('token') as string;
  }

  isTokenValid() {
    const token = this.token;
    if (!token) {
      return false;
    }
    // decode the token
    const jwtHelper = new JwtHelperService();
    // check expiry date
    const isTokenExpired = jwtHelper.isTokenExpired(token);
    if (isTokenExpired) {
      localStorage.clear();
      return false;
    }
    return true;
  }

  isTokenNotValid() {
    return !this.isTokenValid();
  }

  get userRoles(): string[] {
    const token = this.token;
    if (token) {
      const jwtHelper = new JwtHelperService();
      const decodedToken = jwtHelper.decodeToken(token);
      console.log(decodedToken.authorities);
      return decodedToken.authorities;
    }
    return [];
  }

  // 🔥 NUEVO: Método para obtener el nombre completo del usuario
  get userFullName(): string {
    const token = this.token;
    if (token) {
      const jwtHelper = new JwtHelperService();
      const decodedToken = jwtHelper.decodeToken(token);
      
      // Ajusta según la estructura de tu token
      // Puede ser: fullName, name, firstname + lastname, etc.
      return decodedToken.fullName || 
             decodedToken.name || 
             (decodedToken.firstname && decodedToken.lastname 
               ? `${decodedToken.firstname} ${decodedToken.lastname}` 
               : decodedToken.sub || 'User');
    }
    return '';
  }

  // 🔥 OPCIONAL: Solo el primer nombre
  get userFirstName(): string {
    const fullName = this.userFullName;
    return fullName.split(' ')[0];
  }
}