import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthResponse } from '../../models/auth-response';
import { LoginRequest } from '../../models/login-request';
import { RegisterRequest } from '../../models/register-request';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {

  model: any = {}

  error: number = 0;

  constructor(private apiService: ApiService, private router: Router) { }

  ngOnInit(): void {
  }

  onClick() {
    let registerRequest = new RegisterRequest(
      this.model.name,
      this.model.surname,
      this.model.email,
      this.model.password);


    this.apiService.registerUser(registerRequest).subscribe((user) => {
      let loginRequest = new LoginRequest(this.model.email, this.model.password);
      this.apiService.loginUser(loginRequest).subscribe((response) => {
        let authResponse = <AuthResponse>JSON.parse(JSON.stringify(response));
        this.error = 0;
        localStorage.setItem('token', authResponse.accessToken);
        window.location.href = '/';
      }, (error) => {
        this.error = 1;
      })
    }, (error) => {
      this.error = 1;
    })
  }
}
