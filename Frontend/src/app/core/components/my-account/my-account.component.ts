import { Component, OnInit } from '@angular/core';
import { Bid } from '../../models/bid';
import { Category } from '../../models/category';
import { Product } from '../../models/product';
import { User } from '../../models/user';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-my-account',
  templateUrl: './my-account.component.html',
  styleUrls: ['./my-account.component.css']
})
export class MyAccountComponent implements OnInit {

  user!: User;

  sellingProducts!: Product[];

  biddingProducts!: Product[]

  allUserBids!: Bid[];

  allBids!: Bid[]

  listOfNameCategories!:string[];

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    if (localStorage.getItem('token') != null) {
      this.apiService.getCurrentUser().subscribe((user) => {
        this.user = <User>JSON.parse(JSON.stringify(user));
        this.apiService.getAllProducts().subscribe((products) => {
          let allProducts = <Product[]>JSON.parse(JSON.stringify(products));
          this.apiService.getAllBids().subscribe((bids) => {
            this.allBids = <Bid[]>JSON.parse(JSON.stringify(bids));
            allProducts?.length ? this.sellingProducts = allProducts.filter((items) => items.userId == this.user.id) : null;
            this.allUserBids?.length ? this.allUserBids = this.allBids.filter((bidItem) => bidItem.userId == this.user.id) : null;
            allProducts?.length &&  this.allUserBids?.length ? this.biddingProducts = this.findProducts(allProducts, this.allUserBids) : null;
          })
        })
      })
    }
  }

  /**
   * A method that returns all products that are in the list of bids
   * @param products list of all products
   * @param bids list of all user bids
   * @returns list of products on which the user has left a bid
   */

  findProducts(products: Product[], bids: Bid[]): Product[] {
    let resultProducts!: Product[];
    let idsOfBids = bids.map(function (item) {
      return item.productId;
    })
    resultProducts = products.filter((items) => idsOfBids.includes(items.id));
    return resultProducts;
  }
}
