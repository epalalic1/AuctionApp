import { Component, Injectable, Input, OnInit } from '@angular/core';
import { Product } from '../../models/product';
import { ProductImages } from '../../models/product-images';
import { ApiService } from '../../services/api.service';
import { ProductUtils } from '../../utils/product-utils';
import { ItemComponent } from '../item/item.component';

@Component({
  selector: 'app-new-arrivals',
  templateUrl: './new-arrivals.component.html',
  styleUrls: ['./new-arrivals.component.css']
})

@Injectable({
  providedIn: 'root'
})

export class NewArrivalsComponent implements OnInit {

  @Input()
  newArrivals!: Product[];

  @Input()
  listOfProductsImages: ProductImages[] = [];

  constructor(private apiServis: ApiService) { }


  ngOnInit(): void {
    this.apiServis.getNewArrivalsProduct().subscribe((rez) => {
      let products = <Product[]>JSON.parse(JSON.stringify(rez));
      this.newArrivals = products.filter(item => item.status.toString() == 'false');
      setTimeout(() => {
        this.newArrivals = ProductUtils.getImagesOfProduct(this.newArrivals,this.listOfProductsImages)
      }, 1000);
    })
  }
}
