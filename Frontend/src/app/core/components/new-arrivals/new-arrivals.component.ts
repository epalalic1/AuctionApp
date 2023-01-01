import { Component, Injectable, Input, OnInit } from '@angular/core';
import { Product } from '../../models/product';
import { ProductImages } from '../../models/product-images';
import { ApiService } from '../../services/api.service';
<<<<<<< HEAD
import { ProductUtils } from '../../utils/product-utils';
=======
>>>>>>> ec256706 (Add the ability to change the preview of the product as well as change the location where images are retrieved from Firebase)
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
<<<<<<< HEAD
<<<<<<< HEAD
      setTimeout(() => {
        this.newArrivals = ProductUtils.productsWithListOfImages(this.newArrivals,this.listOfProductsImages)
      }, 1000);
=======
>>>>>>> 3c6c8490 (Allow the payment on the Stripe)
=======
      setTimeout(() => {
        this.newArrivals = this.getImagesOfProduct(this.newArrivals);
      }, 1000);
>>>>>>> ec256706 (Add the ability to change the preview of the product as well as change the location where images are retrieved from Firebase)
    })
  }

  getImagesOfProduct(newArr: Product[]) {
    let products =newArr.map((product: Product) => {
      let listOfProductImag = this.listOfProductsImages.filter((item) => item.productId == product.id);
      product.imageName.splice(0);
      listOfProductImag.map((productImg: any) => {
        product.imageName.push(productImg.images);
      })
      return product;
    });
    return products;
  }
}
