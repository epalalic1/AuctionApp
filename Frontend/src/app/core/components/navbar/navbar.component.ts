import { Component, Injectable, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { interval } from 'rxjs';
import Swal from 'sweetalert2';
import { AuthGuard } from '../../guards/auth.guard';
import { User } from '../../models/user';
import { WsNotification } from '../../models/ws-notification';
import { ApiService } from '../../services/api.service';
import { UserService } from '../../services/user-service.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})

@Injectable({
  providedIn: 'root',
})

export class NavbarComponent implements OnInit {

  @ViewChild('myname') input: any;

  router: string = "";

  authGuard!: AuthGuard;

  user: User = new User();

  showNotification: boolean = false;

  isOpen = false;

  listOfNotifications: WsNotification[] = []

  /*listOfNotifications: WsNotification[] = [new WsNotification(
    "Congratulations! You are the highest bidder for the product",
    1, 645, false), new WsNotification(
      "Someone just placed a higher bid than you did on the product",
      1, 645, false), new WsNotification(
        "Congratulations! You are the highest bidder for the product",
        1, 645, false), new WsNotification(
          "Someone just placed a higher bid than you did on the product",
          1, 645, false), new WsNotification(
            "Congratulations! You are the highest bidder for the product",
            1, 645, true), new WsNotification(
              "Someone just placed a higher bid than you did on the product",
              1, 645, true)];*/

  numberOfUnreadNotification! : number ;

  constructor(
    private userService: UserService,
    private _router: Router,
    private authG: AuthGuard,
    private apiService: ApiService) {
    this.authGuard = authG;
    this.router = _router.url;
  }

  ngOnInit(): void {
    if (this.listOfNotifications?.length) {
      this.numberOfUnreadNotification = this.listOfNotifications.filter(item => item.status == false).length;
    }
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

  showNotifications() {
    this.isOpen = !this.isOpen
    this.numberOfUnreadNotification = 0;
    if (this.isOpen == false) {
      this.listOfNotifications.map((item) => {
        item.status = true;
      })
    }
  }

  receiveNotifications(wsNotifications: WsNotification[]) {
    this.listOfNotifications = wsNotifications;
    this.numberOfUnreadNotification = this.listOfNotifications.filter(item => item.status == false).length;
    console.log(this.numberOfUnreadNotification);
    var result = document.getElementsByClassName("mat-badge-content mat-badge-active");
    console.log(result);
    localStorage.setItem("numberOfUnreadNotification", this.numberOfUnreadNotification.toString());
  }
}
