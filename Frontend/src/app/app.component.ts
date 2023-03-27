import { Component, ElementRef, EventEmitter, Output, ViewChild } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { Response } from './core/models/response';
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { ApiService } from './core/services/api.service';
import { AuthGuard } from './core/guards/auth.guard';
import { environment } from 'src/environments/environments';
import { Product } from './core/models/product';
import { getStorage, ref, getDownloadURL } from "firebase/storage";
import { ProductImages } from './core/models/product-images';
import { WebSocketAPI } from './core/models/web-socket-api';
import { WsNotification } from './core/models/ws-notification';
import { NavbarComponent } from './core/components/navbar/navbar.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  preserveWhitespaces: false
})

export class AppComponent {
  title = 'AuctionApp';

  authG!: AuthGuard;

  listOfProductsImages : ProductImages[] = [];

  webSocketAPI!: WebSocketAPI;

  greeting: any;

  size :number = 0;

  constructor(
    private router: Router,
    private apiService: ApiService,
    private authGuard: AuthGuard,
    private navbarComponent: NavbarComponent
  ) {
    this.authG = authGuard;
  }

  ngOnInit() {
    if (localStorage.getItem('token') != null) {
      this.webSocketAPI = new WebSocketAPI(new AppComponent(this.router,this.apiService,this.authG,this.navbarComponent),this.apiService);
      this.connect();
      setTimeout(
        () => {
          this.router.navigate(['/']).then(()=>
          setTimeout(() => {
            window.alert('You have just logged out.');
            }, 700))
          localStorage.removeItem('token');
        }, 86400000);
    }

    this.apiService.intializeDatabaseTables().subscribe((rez) => {
      console.log(rez);
    } , (error) => {
      window.alert("An error occurred during initialization, please reload the application");
    });
    this.router.events.subscribe((evt) => {
      if (!(evt instanceof NavigationEnd)) {
        return;
      }
      window.scrollTo(0, 0)
    });

    const firebaseConfig = {
      apiKey: environment.firebaseConfig.apiKey,
      authDomain:  environment.firebaseConfig.authDomain,
      projectId:  environment.firebaseConfig.projectId,
      storageBucket:  environment.firebaseConfig.storageBucket,
      messagingSenderId:  environment.firebaseConfig.messagingSenderId,
      appId: environment.firebaseConfig.appId,
      measurementId:  environment.firebaseConfig.measurementId
    };
    const app = initializeApp(firebaseConfig);
    const analytics = getAnalytics(app);

    this.apiService.getAllProducts().subscribe((products) => {
      let allProducts = <Product[]>JSON.parse(JSON.stringify(products));
      if (allProducts?.length) {
        for (let product of allProducts) {
          const storage = getStorage();
          for (const img of product.imageName) {
            getDownloadURL(ref(storage, img))
              .then((url) => {
                let productImages = new ProductImages(product.id, url);
                this.listOfProductsImages.push(productImages)
              })
          }
        }
      }
    })
  }

  /**
   * The method that is called when we want to check if there is a product whose auction has ended in
   *  order to send a notification to the users with the highest bids
   */

  checkIfFinishedAuctionExists() {
      let date = new Date();
      if (date.getHours() == 0 && date.getMinutes() == 1 && date.getSeconds() == 0) {
          this.webSocketAPI._sendPrivateFinishedAuction();
      }
  }

  changeNumberOfNotf(val: number) {
    this.size = val;
  }

  messageReceive() {
      this.size += 1;
      let element:HTMLElement = document.getElementById('auto_trigger') as HTMLElement;
      element.click();
  }

  connect(){
    this.webSocketAPI._connect();
  }

  onClick(wsNotification: WsNotification) {
    this.webSocketAPI._sendPrivate(wsNotification);
  }

  hasRoute(route: string) {
    return this.router.url.includes(route);
  }
}
