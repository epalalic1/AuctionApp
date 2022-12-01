import { Component } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { InitializeService } from './initialize.service';
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { environment } from './../environments/environment';
import { Response } from './core/models/response';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'AuctionApp';

  constructor(
    private router: Router,
    private initializeService: InitializeService
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
