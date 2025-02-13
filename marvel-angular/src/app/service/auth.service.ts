import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private isAuth = false;

  login(username: string, password: string): boolean {
    if (!username || !password) {  //  Bloquea credenciales vacías
      return false;
    }
  
    // 🔹 Simulación de autenticación correcta
    if (username === 'admin' && password === '8956') { 
      this.isAuth = true;
      localStorage.setItem('isAuth', 'true');
      return true;
    }
  
    return false;

  }
  logout(): void {
    this.isAuth = false;
    localStorage.removeItem('isAuth');
  }

  isLoggedIn(): boolean {
    return localStorage.getItem('isAuth') === 'true';
  }
  constructor() { }
}
