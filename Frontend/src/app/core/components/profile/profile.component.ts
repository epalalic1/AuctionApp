import { Component, Input, OnInit } from '@angular/core';
import { UpdateUser } from '../../models/update-user';
import { User } from '../../models/user';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  model: any = {}

  @Input()
  user: User = new User();

  constructor(private apiService: ApiService) { }

  ngOnInit(): void { }

  /**
   * A method that is triggered when the user clicks the "Save info" button 
   * and then sends a put request to the server along with the new data
   */

  onClick() {
    if (this.validateInput(this.model.firstName) && this.validateInput(this.model.lastName)
      && this.validateInput(this.model.email) && this.validateInput(this.model.phone)) {
      return
    }
    else {
      let updateUser = new UpdateUser();
      this.validateInput(this.model.firstName) ? updateUser.firstName = this.user.name : updateUser.firstName = this.model.firstName;
      this.validateInput(this.model.lastName) ? updateUser.lastName = this.user.surname : updateUser.lastName = this.model.lastName;
      this.validateInput(this.model.email) ? updateUser.email = this.user.email : updateUser.email = this.model.email;
      this.validateInput(this.model.phone) ? updateUser.phone = this.user.phone : updateUser.phone = this.model.phone;
      this.apiService.updateUser(updateUser).subscribe((user) => {
        window.location.reload();
      })
    }

  }

  validateInput(input: String) {
    return input == undefined ? true : false
  }

}
