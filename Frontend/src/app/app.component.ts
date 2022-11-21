import { Component } from '@angular/core';
import { Router,NavigationEnd  } from '@angular/router';
import { InitializeService } from './initialize.service';
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'AuctionApp';
  constructor(
    private router: Router,
    private service:InitializeService
  ) {}

  ngOnInit() {
    this.router.events.subscribe((evt) => {
      if (!(evt instanceof NavigationEnd)) {
          return;
      }
      window.scrollTo(0, 0)
  });
  this.service.intializeDatabaseTables().subscribe((rez) =>{
    
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
  
  // Initialize Firebase
  const app = initializeApp(firebaseConfig);
  const analytics = getAnalytics(app);
  
  

  }
  hasRoute(route: string) {
    return this.router.url.includes(route);
  }

  
}
