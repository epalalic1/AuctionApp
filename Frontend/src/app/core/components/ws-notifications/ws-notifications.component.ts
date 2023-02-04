import { Component, Input, OnInit } from '@angular/core';
import { Product } from '../../models/product';
import { WsNotification } from '../../models/ws-notification';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-ws-notifications',
  templateUrl: './ws-notifications.component.html',
  styleUrls: ['./ws-notifications.component.css']
})
export class WsNotificationsComponent implements OnInit {

  /*notification = new WsNotification(
    "Congratulations! You are the highest bidder for the product",
    1, 645, false);*/

  /* notification = new WsNotification(
     "Congratulations! You are the highest bidder for the product",
     1, 645, false);
     Someone just placed a higher bid than you did on the product
     */

  @Input()
  notification!: WsNotification;

  product = new Product;

  typeOfNotification = false;

  color!: string;

  constructor(private apiServis: ApiService) { }

  ngOnInit(): void {
    this.notification.message[0] == "S" ? this.typeOfNotification = true : this.typeOfNotification = false;
    this.notification.status == false ? this.color = "lightgrey" : this.color = "#E3E3E3"
    this.apiServis.getAllProducts().subscribe((products) => {
      let allProducts = <Product[]>JSON.parse(JSON.stringify(products));
      this.product = allProducts.filter(item => item.id == this.notification.productId)[0];
    })
  }

  goToButton() {
    window.location.href = '/Product;id=' + this.product.id;
  }

}
