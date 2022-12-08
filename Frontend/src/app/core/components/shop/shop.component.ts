import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../../models/product';
import { ApiService } from '../../services/api.service';
import { NewArrivalsService } from '../../services/new-arrivals.service';
import { ProductUtils } from '../../utils/product-utils';


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

  value: number = 0;

  constructor(
    private route: ActivatedRoute,
    private apiServis: ApiService,
    private newArriwals: NewArrivalsService) { }

  ngOnInit(): void {
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
            let filteredProducts = this.allProducts.filter((item) => ProductUtils.compareTwoStrings(item.name, this.search) >= 0.7);
            filteredProducts = filteredProducts.sort((a, b) => {
              if (ProductUtils.compareTwoStrings(a.name, this.search) > ProductUtils.compareTwoStrings(b.name, this.search))
                return -1;
              return 1;
            });
            this.didYouMeanProduct = filteredProducts[0].name;
            this.value = 2;
          }
        })
      }
    })
  }

  getList(ev: Product[]) {
    this.didYouMeanProduct = "";
    this.products = ev;
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
    }
  }
}
