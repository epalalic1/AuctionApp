import { Component } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
<<<<<<< HEAD
import { InitializeService } from './initialize.service';
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { environment } from './../environments/environment';
import { Response } from './core/models/response';
=======
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { ApiService } from './core/services/api.service';
>>>>>>> 67f9812b (Code formatting)

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'AuctionApp';

  constructor(
    private router: Router,
<<<<<<< HEAD
    private initializeService: InitializeService
=======
    private apiService : ApiService
>>>>>>> 67f9812b (Code formatting)
  ) { }

  ngOnInit() {

    this.initializeService.intializeDatabaseTables().subscribe((rez) => {
          let response = <Response> JSON.parse(JSON.parse(rez));
          console.log(response.message);
    });
    this.router.events.subscribe((evt) => {
      if (!(evt instanceof NavigationEnd)) {
        return;
      }
      window.scrollTo(0, 0)
    });
<<<<<<< HEAD
=======

    this.apiService.intializeDatabaseTables().subscribe((rez) => {
    });

    const firebaseConfig = {
      apiKey: "AIzaSyCwxApUye4bTdZcuVzFsNyUlUZ3EkxMSAo",
      authDomain: "mythical-bazaar-350813.firebaseapp.com",
      projectId: "mythical-bazaar-350813",
      storageBucket: "mythical-bazaar-350813.appspot.com",
      messagingSenderId: "721663920426",
      appId: "1:721663920426:web:22c68291eefa6439cb3653",
      measurementId: "G-053BJYKGTE"
    };

    const app = initializeApp(firebaseConfig);
    const analytics = getAnalytics(app);
>>>>>>> 67f9812b (Code formatting)

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
