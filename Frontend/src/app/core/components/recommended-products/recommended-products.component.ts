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
    this.apiService.getAllBids().subscribe((rez) => {
      this.bids = <Bid[]>JSON.parse(JSON.stringify(rez));
      this.apiService.getAllProducts().subscribe((rez) => {
        this.products = <Product[]>JSON.parse(JSON.stringify(rez));
        this.products.map((rez) => {
          if (rez.userId == this.user.id) {
            this.listOfCategories.push(rez.categoryId);
            this.listOfSubcategories.push(rez.subcategoryId)
          }
        })
        this.bids.map((rez) => {
          if (rez.userId == this.user.id) {
            this.listOfProductsFromBidding.push(rez.productId);
          }
        })
        this.products.map((rez) => {
          if (this.listOfProductsFromBidding.includes(rez.id)) {
            this.listOfCategories.push(rez.categoryId);
            this.listOfSubcategories.push(rez.subcategoryId);
          }
        })
        this.recommendedProducts = this.products.filter((rez) => rez.userId != this.user.id && (this.listOfCategories.includes(rez.categoryId) || this.listOfSubcategories.includes(rez.subcategoryId)))
      })
    })
  }
}
