import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { AuthService } from "../../service/auth.service";
import { FormsModule } from "@angular/forms";
import { CommonModule } from '@angular/common'; 

@Component({
  selector: 'app-login',
  standalone: true,  // Hacemos el componente autónomo
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  imports: [FormsModule, CommonModule]  // Importamos FormsModule directamente aquí
})

export class LoginComponent {
  username = 'admin';
  password = '8956';
  loginError = false;

  constructor(private authService: AuthService, private router: Router) {}

  onLogin(): void {
    if (this.authService.login(this.username, this.password)) {
      this.router.navigate(['/characters']);
    } else {
      this.loginError = true;
    }
  }
}
