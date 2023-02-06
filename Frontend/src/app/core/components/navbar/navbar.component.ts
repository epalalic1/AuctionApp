import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { AuthGuard } from '../../guards/auth.guard';
import { User } from '../../models/user';
import { ApiService } from '../../services/api.service';
import { UserService } from '../../services/user-service.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  router: string = "";

  authGuard!: AuthGuard;

  user: User = new User();

  constructor(
    private userService: UserService,
    private _router: Router,
    private authG: AuthGuard,
    private apiService: ApiService) {
    this.authGuard = authG;
    this.router = _router.url;
  }

  ngOnInit(): void {
    if (localStorage.getItem('token') != null) {
      this.apiService.getCurrentUser().subscribe((user) => {
        this.user = <User>JSON.parse(JSON.stringify(user));
      })
    }
  }

  onClick(): void {
    let input = (document.getElementById("serachBar") as HTMLInputElement)!.value;
    this._router.navigate(['/Category', { search: input }]);
  }

  myAccountAction(): void {
    if (localStorage.getItem('token') != null) {
      this._router.navigate(['myAccount']);
    }
    else {
      window.alert("Log in to an existing account or create a new one to access this page");
    }
  }

  reload(): void {
    window.location.reload();
  }

  /**
   * A method that is triggered when the user clicks the "Log out" button and logs the user out 
   * of the profile
   */

  logOut() {
    this._router.navigate(['/']).then(() =>
      setTimeout(() => {
        window.alert('You have just logged out.');
      }, 700))
    localStorage.removeItem('token');
  }
}
