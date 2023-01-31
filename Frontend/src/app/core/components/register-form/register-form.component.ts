import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthResponse } from '../../models/auth-response';
import { LoginRequest } from '../../models/login-request';
import { RegisterRequest } from '../../models/register-request';
import { ApiService } from '../../services/api.service';
import { ProductUtils } from '../../utils/product-utils';
import { SecurityUtils } from '../../utils/security';
import { Validation } from '../../utils/validation';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {

  model: any = {}

  error: number = 0;

  validateName!: number;

  validateSurname!: number;

  validateEmail !: number;

  validatePassword !: number;

  constructor(private apiService: ApiService, private router: Router) { }

  ngOnInit(): void {
  }

  onClick() {
    if (this.validateName == 1 && this.validateSurname == 1 && this.validateEmail == 1 && this.validatePassword == 1) {
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
          this.error = 0;
        }, (error) => {
          this.error = 1;
        })
      }, (error) => {
        this.error = 1;
      })
    }
    else {
      this.validateName == undefined ? this.validateName = 0 : null;
      this.validateSurname == undefined ? this.validateSurname = 0 : null;
      this.validateEmail == undefined ? this.validateEmail = 0 : null;
      this.validatePassword == undefined ? this.validatePassword = 0 : null
    }
  }

  /**
   * Method that tracks changes when entering a name
   * @param event when user input some value
   */

  changeName(event: any) {
    this.model.name == undefined ||  this.model.surname == "" || !Validation.checkIsNameSurnameValid(this.model.name) ? this.validateName = 0 : this.validateName = 1;
  }

  /**
   * Method that tracks changes when entering a last name
   * @param event when user input some value
   */

  changeLastName(event: any) {
    this.model.surname == undefined || this.model.surname == ""  || !Validation.checkIsNameSurnameValid(this.model.name) ? this.validateSurname = 0 : this.validateSurname = 1;
  }

  /**
   * Method that tracks changes when entering an email
   * @param event when user input some value
   */

  changeEmail(event: any) {
    this.model.email == undefined || !Validation.checkIfEmailIsValid(this.model.email)  ||  this.model.email == "" ? this.validateEmail = 0 : this.validateEmail = 1;
  }

  /**
   * Method that tracks changes when entering a password
   * @param event when user input some value
   */

  changePassword(event: any) {
    this.model.password == undefined || !Validation.chackIfPasswordIsValid(this.model.password) || this.model.password == "" ? this.validatePassword = 0 : this.validatePassword = 1;
  }
}
