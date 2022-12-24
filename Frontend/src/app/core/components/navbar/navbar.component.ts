import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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

  reload(): void {
    window.location.reload();
  }
}
