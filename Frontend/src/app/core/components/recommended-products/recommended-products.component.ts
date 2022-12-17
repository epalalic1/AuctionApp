import { JsonPipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Bid } from '../../models/bid';
import { Product } from '../../models/product';
import { User } from '../../models/user';
import { ApiService } from '../../services/api.service';

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

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    /*For now we are using this user but after implementation of registration we are going to use logged in users*/
    this.user = new User(1, "user", "user", "user", "user", "user", "user", "user", 1);
    this.apiService.getAllBids().subscribe((bidResponse) => {
      this.bids = <Bid[]>JSON.parse(JSON.stringify(bidResponse));
      this.apiService.getAllProducts().subscribe((productResponse) => {
        this.products = <Product[]>JSON.parse(JSON.stringify(productResponse));
        this.findSimilarProductsFromSelling(this.products);
        this.findSimilarProductsFromBidding(this.bids, this.products);
        this.recommendedProducts = this.products.filter((item) => item.userId != this.user.id &&
          (this.listOfCategories.includes(item.categoryId) || this.listOfSubcategories.includes(item.subcategoryId)))
      })
    })
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
