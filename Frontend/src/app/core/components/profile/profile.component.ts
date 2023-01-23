import { Component, Input, OnInit } from '@angular/core';
import { UpdateUser } from '../../models/update-user';
import { User } from '../../models/user';
import { ApiService } from '../../services/api.service';
import { Validation } from '../../utils/validation';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  model: any = {}

  @Input()
  user: User = new User();

  validatePhone = 0;

  validateEmailInput !: number;

  constructor(private apiService: ApiService) { }

  ngOnInit(): void { }

  /**
   * A method that is triggered when the user clicks the "Save info" button 
   * and then sends a put request to the server along with the new data
   */

  onClick() {
    if (this.validateInput(this.model.firstName) && this.validateInput(this.model.lastName)
      && this.validateEmail(this.model.email) && this.validatePhoneNumber(this.model.phone)) {
      return
    }
    else {
      let updateUser = new UpdateUser();
      this.validateInput(this.model.firstName) ? updateUser.firstName = this.user.name : updateUser.firstName = this.model.firstName;
      this.validateInput(this.model.lastName) ? updateUser.lastName = this.user.surname : updateUser.lastName = this.model.lastName;
      this.validateEmail(this.model.email) ? updateUser.email = this.user.email : updateUser.email = this.model.email;
      this.validatePhoneNumber(this.model.phone) ? updateUser.phone = this.user.phone : updateUser.phone = this.model.phone;
      this.apiService.updateUser(updateUser).subscribe((user) => {
        window.location.reload();
      })
    }
  }

  /**
   * A method that checks whether a string is undefined or empty
   * @param input string that we check
   * @returns returns a boolean depending on whether the input meets the conditions
   */

  validateInput(input: String) {
    return input == undefined || input == "" ? true : false
  }

  /**
   * A method that checks whether an email is undefined, empty or does not contain characters
   *  that an email must contain
   * @param input string that we check
   * @returns returns a boolean depending on whether the email meets the conditions
   */

  validateEmail(input: string) {
    return input == undefined || input == "" ? true : this.validateEmailInput == 0 ? true : false;
  }

  /**
   * Method that tracks changes when entering a phone number
   * @param event when user input some value
   */

  changePhone(event: any) {
   !Validation.checkIfPhoneIsValid(this.model.phone) ? this.validatePhone = 0 : this.validatePhone = 1;
  }

  /**
   *  A method that checks whether a phone number is undefined or empty, or does not contain characters
   *  that a phone number must contain
   * @param input phone number we check
   * @returns returns a boolean depending on whether the phone number meets the conditions
   */

  validatePhoneNumber(input: string) {
    return input == undefined || input == "" ? true : this.validatePhone == 0 ? true : false;
  }

  /**
   * Method that tracks changes when entering an email
   * @param event when user input some value
   */

  changeEmail(event: any) {
    this.model.email == "" || Validation.checkIfEmailIsValid(this.model.email) ? this.validateEmailInput = 1 : this.validateEmailInput = 0;
  }
}
