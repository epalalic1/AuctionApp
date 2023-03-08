import { Component, Injectable, Input, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { NavigationStart, Router } from '@angular/router';
import { interval, Subscription } from 'rxjs';
import Swal from 'sweetalert2';
import { AuthGuard } from '../../guards/auth.guard';
import { User } from '../../models/user';
import { WsNotification } from '../../models/ws-notification';
import { ApiService } from '../../services/api.service';
import { UserService } from '../../services/user-service.service';
import { ChangeDetectorRef } from '@angular/core';

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

  proba: number = 0;

  listOfNotifications: WsNotification[] = []


  @Input() size!: number;


  numberOfUnreadNotification!: number;

  subscription!: Subscription;
  pageReloading: boolean = false;

  constructor(
    private userService: UserService,
    private _router: Router,
    private authG: AuthGuard,
    private apiService: ApiService,
    private ref: ChangeDetectorRef) {
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
    this.ref.markForCheck();
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

  /**
   * A method that is triggered when the user clicks on the notification bell. If the isOpen parameter is 
   * true, it shows notifications, and if it is false, then notifications are not displayed
   */

  showNotifications() {
    this.isOpen = !this.isOpen
    let numberOfUnreadNotification = this.size;
    this.size = 0;
    if (this.isOpen == true) {
      this.apiService.getNotifications(this.user.id).subscribe((items) => {
        this.listOfNotifications = <WsNotification[]>JSON.parse(JSON.stringify(items));
        this.differentiateNotifications(numberOfUnreadNotification);
      })
    }
  }

  /**
   * The method that performs distinguishes between read and unread methods depending on the 
   * number passed as a parameter
   * @param numberOfUnReadNotificatins is number that represents the number od unread notifications
   */

  differentiateNotifications(numberOfUnReadNotificatins: number) {
    if (numberOfUnReadNotificatins != 0) {
      for (let i = 0; i < numberOfUnReadNotificatins; i++) {
        this.listOfNotifications[i].status = false;
      }
      for (let i = numberOfUnReadNotificatins; i < this.listOfNotifications.length; i++) {
        this.listOfNotifications[i].status = true;
      }
    }
    else {
      this.listOfNotifications.map((item) => {
        item.status = true;
      })
    }
  }
}
