import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthResponse } from '../../models/auth-response';
import { LoginRequest } from '../../models/login-request';
import { RegisterRequest } from '../../models/register-request';
import { ApiService } from '../../services/api.service';
import { SecurityUtils } from '../../utils/security';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {

  model: any = {}

  error: number = 0;

  validateName = 1;

  validateSurname = 1;

  validateEmail = 1;

  validatePassword = 1;

  validRegexEmail = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

  validRegexPassword = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W)/; 

  constructor(private apiService: ApiService, private router: Router) { }

  ngOnInit(): void {
  }

  onClick() {
    this.validateUsersInput();
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
        localStorage.setItem('token', SecurityUtils.encrypt(authResponse.accessToken))
        window.location.href = '/';
      }, (error) => {
        this.error = 1;
      })
    }, (error) => {
      this.error = 1;
    })
  }

  validateUsersInput() {
    this.model.name == undefined || this.model.name == "" ? this.validateName = 0 : this.validateName = 1;
    this.model.surname == undefined || this.model.surname == "" ? this.validateSurname = 0 : this.validateSurname = 1;
    this.model.email == undefined  || this.model.email == "" || !this.model.email.match(this.validRegexEmail) ? this.validateEmail = 0 : this.validateEmail = 1;
    this.model.password == undefined || this.model.password == "" || !this.model.password.match(this.validRegexPassword) ? this.validatePassword = 0 : this.validatePassword = 1;
  }
}
