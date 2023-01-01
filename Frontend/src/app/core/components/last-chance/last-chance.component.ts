import { Component, Input, OnInit } from '@angular/core';
import { Product } from '../../models/product';
import { ProductImages } from '../../models/product-images';
import { ApiService } from '../../services/api.service';
import { BidService } from '../../services/bid.service';

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
      setTimeout(() => {
        this.lastChanceProducts = this.getImagesOfProduct(this.lastChanceProducts);
      }, 1000);
    })
    this.bidService.getBids();
  }

  getImagesOfProduct(lastChance: Product[]) {
    let products = lastChance.map((product) => {
      let listOfProductImag = this.listOfProductsImages.filter(item => item.productId == product.id);
      product.imageName.splice(0);
      listOfProductImag.map((productImg: any) => {
        product.imageName.push(productImg.images);
      })
      return product;
    });
    return products;
  }
}

