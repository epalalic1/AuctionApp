import { Component, OnInit } from '@angular/core';
import { AuthResponse } from '../../models/auth-response';
import { LoginRequest } from '../../models/login-request';
import { ApiService } from '../../services/api.service';
import { Router } from '@angular/router';
import { UserService } from '../../services/user-service.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  model: any = {}

  error: number = 0;

  constructor(private apiService: ApiService, private router: Router) { }

  ngOnInit(): void {
  }

  onClick() {
    let loginRequest = new LoginRequest(this.model.email, this.model.password);
    this.apiService.loginUser(loginRequest).subscribe((response) => {
      let authResponse = <AuthResponse>JSON.parse(JSON.stringify(response));
      this.error = 0;
      localStorage.setItem('token', authResponse.accessToken);
      window.location.href = '/';
    }, (error) => {
      this.error = 1;
    })
  }
}
