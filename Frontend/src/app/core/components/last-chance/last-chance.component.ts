import { Component, Input, OnInit } from '@angular/core';
import { Product } from '../../models/product';
import { ProductImages } from '../../models/product-images';
import { ApiService } from '../../services/api.service';
import { BidService } from '../../services/bid.service';
import { ProductUtils } from '../../utils/product-utils';

@Component({
  selector: 'app-last-chance',
  templateUrl: './last-chance.component.html',
  styleUrls: ['./last-chance.component.css']
})

export class LastChanceComponent implements OnInit {

  lastChanceProducts!: Product[];

  usersRole: number = 0;

  @Input()
  listOfProductsImages: ProductImages[] = [];

  constructor(private bidService: BidService,
    private apiService: ApiService) { }

  ngOnInit(): void {
    this.apiService.getLastChanceProduct().subscribe((rez) => {
      let products = <Product[]>JSON.parse(JSON.stringify(rez));
      this.lastChanceProducts = products.filter(item => item.status.toString() == 'false');
<<<<<<< HEAD
      setTimeout(() => {
        this.lastChanceProducts = ProductUtils.productsWithListOfImages(this.lastChanceProducts,this.listOfProductsImages)
      }, 1000);
=======
>>>>>>> 3c6c8490 (Allow the payment on the Stripe)
    })
    this.bidService.getBids();
  }
}
