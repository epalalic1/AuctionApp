import { Component } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { Response } from './core/models/response';
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { ApiService } from './core/services/api.service';
import { environment } from 'src/environments/environments';
import { UserService } from './core/services/user-service.service';
import { AuthGuard } from './core/guards/auth.guard';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'AuctionApp';

  authG!: AuthGuard;


  constructor(
    private router: Router,
    private apiService: ApiService,
    private authGuard: AuthGuard
  ) {
    this.authG = authGuard;
  }

  ngOnInit() {
    if (localStorage.getItem('token') != null) {
      setTimeout(
         () => {
          this.router.navigate(['/'])
          localStorage.removeItem('token');
        }, 60000);
    }
    this.apiService.intializeDatabaseTables().subscribe((rez) => {
      let response = <Response>JSON.parse(JSON.stringify(rez));
      console.log(response);
    });
    this.router.events.subscribe((evt) => {
      if (!(evt instanceof NavigationEnd)) {
        return;
      }
      window.scrollTo(0, 0)
    });

    const firebaseConfig = {
      apiKey: environment.firebaseConfig.apiKey,
      authDomain: environment.firebaseConfig.authDomain,
      projectId: environment.firebaseConfig.projectId,
      storageBucket: environment.firebaseConfig.storageBucket,
      messagingSenderId: environment.firebaseConfig.messagingSenderId,
      appId: environment.firebaseConfig.appId,
      measurementId: environment.firebaseConfig.measurementId
    };
    const app = initializeApp(firebaseConfig);
    const analytics = getAnalytics(app);
  }

  hasRoute(route: string) {
    return this.router.url.includes(route);
  }
}
