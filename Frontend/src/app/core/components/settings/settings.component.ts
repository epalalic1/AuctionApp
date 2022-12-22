import { Component, Input, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  @Input()
  user!: User;

  constructor(private apiService:ApiService) { }

  ngOnInit(): void { }

  /**
   * A method that is triggered when the user clicks the "Deactivate" button 
   * and creates an api call to the server that deactivates the currently logged-in user's profile
   */

  deactivate() {
    var answer = window.confirm("Are you sure you want to deactivate the profile?");
    if (answer) {
      this.apiService.deleteUser().subscribe((response) => {
        localStorage.removeItem('token');
        window.location.href = '/';
      })
    }
  }

}
