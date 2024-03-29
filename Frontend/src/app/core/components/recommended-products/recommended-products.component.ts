import { JsonPipe } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { Bid } from '../../models/bid';
import { Product } from '../../models/product';
import { ProductImages } from '../../models/product-images';
import { User } from '../../models/user';
import { ApiService } from '../../services/api.service';
import { ProductUtils } from '../../utils/product-utils';

@Component({
  selector: 'app-recommended-products',
  templateUrl: './recommended-products.component.html',
  styleUrls: ['./recommended-products.component.css']
})
export class RecommendedProductsComponent implements OnInit {

  bids: Bid[] = []

  products: Product[] = []

  user!: User

  listOfCategories: number[] = []

  listOfSubcategories: number[] = []

  listOfProductsFromBidding: number[] = []

  recommendedProducts: Product[] = []

  @Input()
  listOfProductsImages: ProductImages[] = [];

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    if (localStorage.getItem('token') != null) {
      this.apiService.getCurrentUser().subscribe((user) => {
        this.user = <User>JSON.parse(JSON.stringify(user));
        this.apiService.getAllBids().subscribe((bidResponse) => {
          this.bids = <Bid[]>JSON.parse(JSON.stringify(bidResponse));
          this.apiService.getAllProducts().subscribe((productResponse) => {
            this.products = <Product[]>JSON.parse(JSON.stringify(productResponse));
            if (this.products?.length) {
              setTimeout(() => {
                this.products = ProductUtils.productsWithListOfImages(this.products, this.listOfProductsImages)
              }, 1000);
              this.findSimilarProductsFromSelling(this.products);
              this.findSimilarProductsFromBidding(this.bids, this.products);
              this.recommendedProducts = this.findRecommendedProducts();
            }
          })
        })
      })
    }
  }

  /**
   * A method that finds the categories and subcategories to which the products sold by the user belong
   * @param products list of all products that we map to find categories and subcategories
   */

  findSimilarProductsFromSelling(products: Product[]) {
    products.map((item) => {
      if (item.userId == this.user.id) {
        this.listOfCategories.push(item.categoryId);
        this.listOfSubcategories.push(item.subcategoryId)
      }
    })
  }

  /**
   * A method that finds categories and subcategories of products on which the user  left an bid
   * @param bids list of all bids in database
   * @param products list of all products that we map to find categories and subcategories
   */

  findSimilarProductsFromBidding(bids: Bid[], products: Product[]) {
    if (bids?.length) {
      bids.map((item) => {
        if (item.userId == this.user.id) {
          this.listOfProductsFromBidding.push(item.productId);
        }
      })
      products.map((item) => {
        if (this.listOfProductsFromBidding.includes(item.id)) {
          this.listOfCategories.push(item.categoryId);
          this.listOfSubcategories.push(item.subcategoryId);
        }
      })
    }
  }

  /**
   * A method that finds recommended products for specific user
   * @returns list of products
   */

  findRecommendedProducts() {
    let recProducts = this.products.filter((item) => item.userId != this.user.id &&
      (this.listOfCategories.includes(item.categoryId) || this.listOfSubcategories.includes(item.subcategoryId)))
    if (recProducts.length < 8) {
      for (var i = 0; i <= 8; i++) {
        recProducts.push(this.products[i]);
        if (recProducts.length == 8) {
          break;
        }
      }
    }
    return recProducts
  }
}
