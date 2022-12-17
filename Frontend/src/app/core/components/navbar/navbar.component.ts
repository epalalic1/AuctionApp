import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
<<<<<<< HEAD
=======
import { AuthGuard } from '../../guards/auth.guard';
import { User } from '../../models/user';
import { ApiService } from '../../services/api.service';
import { UserService } from '../../services/user-service.service';
>>>>>>> fb6fb406 (Enable login and registration from the frontend)

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  router: string = "";

  authGuard!: AuthGuard;

  user : User = new User(1,"","","","","","","",0);

  constructor(private userService: UserService,
    private _router: Router,
    private authGuard1: AuthGuard,
    private apiService:ApiService) {
    this.authGuard = authGuard1;
    this.router = _router.url;
  }

  onClick(): void {
    let input = (document.getElementById("serachBar") as HTMLInputElement)!.value;
    this.router.navigate(['/Category', { search: input }]);

  ngOnInit(): void {
    if (localStorage.getItem('token')!=null) {
        this.apiService.getCurrentUser().subscribe((user)=>{
            this.user = <User> JSON.parse(JSON.stringify(user));
        })
    }
  }

  reload(): void {
    window.location.reload();
  }
}
