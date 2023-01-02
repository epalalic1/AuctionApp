import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { Product } from '../../models/product';
import { ApiService } from '../../services/api.service';
import { ShopService } from '../../services/shop.service';
import { ProductUtils } from '../../utils/product-utils';
import { NewArrivalsComponent } from '../new-arrivals/new-arrivals.component';


@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {

  products: Product[] = []

  clicked: boolean = false;

  iterator: number = 0;

  optionalProducts: Product[] = [];

  end: boolean = false;

  search: string = "";

  allProducts: Product[] = [];

  firstProduct!: Product;

  didYouMeanProduct: string = "";

  showNotification: number = 0;

  grid:number = 0;
  
  list:number = 0;

  constructor(
    private route: ActivatedRoute,
    private apiServis: ApiService,
    private shopService: ShopService,
    private appComponent: AppComponent) { }

  ngOnInit(): void {
    this.grid = 1;
    this.list = 0;
    this.didYouMeanProduct = "";
    this.end = true;
    this.route.paramMap.subscribe(paramMap => {
      this.search = paramMap.get('search')!;
      if (this.search != null) {
        this.products.splice(0);
        this.optionalProducts.splice(0);
        this.apiServis.getAllProducts().subscribe((rez) => {
          this.allProducts = <Product[]>JSON.parse(JSON.stringify(rez));
          this.products = this.allProducts.filter((item) => item.name.toLocaleLowerCase() === this.search.toLocaleLowerCase());
          if (this.products.length == 0) {
            this.didYouMeanProduct = this.shopService.findDidYouMeanProduct(this.allProducts, this.search);
            this.showNotification = 1;
          }
          this.default();
        })
      }
    })
  }

  getList(ev: Product[]) {
    this.didYouMeanProduct = "";
    this.products = ev;
    this.default();
    this.optionalProducts.splice(0);
    this.iterator = 0;
    if (this.products.length < 9) {
      this.clicked = false;
      this.end = true;
    }
  }

  onClick(): void {
    this.iterator = this.iterator + 1;
    this.clicked = true;
    let start = this.iterator * 9;
    for (let i = start; i < start + 9; i++) {
      if (i == this.products.length) {
        this.end = false;
        break;
      }
      this.optionalProducts.push(this.products[i]);
      this.optionalProducts = ProductUtils.getImagesOfProduct(this.optionalProducts,this.appComponent.listOfProductsImages)
    }
  }

  /**
  * A method that sorts the displayed products by name
  */

  default(): void {
    if (this.products.length != 0) {
      this.products.sort((firstItem, secondItem) => firstItem.name.localeCompare(secondItem.name))
    }
  }

  /**
  * A method that sorts the displayed products by date of creating (arriving)
  */

  newToOld(): void {
    if (this.products.length != 0) {
      this.products.sort((firstItem, secondItem) => new Date(secondItem.dateOfArriving).getTime() - new Date(firstItem.dateOfArriving).getTime());
    }
  }

  /**
  * A method that sorts the displayed products by end date
  */

  timeLeft(): void {
    if (this.products.length != 0) {
      this.products.sort((firstItem, secondItem) => new Date(secondItem.endDate).getTime() - new Date(firstItem.endDate).getTime());
    }
  }

  /**
  *A method that sorts the displayed products by start price from lowest to highest
  */

  lowToHigh(): void {
    if (this.products.length != 0) {
      this.products.sort((firstItem, secondItem) => firstItem.startPrice - secondItem.startPrice);
    }
  }

  /**
  * A method that sorts the displayed products by start price from highest to lowest
 */

  highToLow(): void {
    if (this.products.length != 0) {
      this.products.sort((firstItem, secondItem) => secondItem.startPrice - firstItem.startPrice);
    }
  }

  gridPreview(){
    this.grid = 1;
    this.list = 0;
  }

  listPreview(){
    this.list = 1;
    this.grid = 0;
  }
}




