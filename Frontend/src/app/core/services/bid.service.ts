import { Injectable } from '@angular/core';
import { Bid } from '../models/bid';
import { User } from '../models/user';
import { ApiService } from './api.service';


@Injectable({
  providedIn: 'root'
})
export class BidService {

  listOfBids: Bid[] = [];
  
  constructor(private apiServis: ApiService) {
  }

  getBids(): void {
    this.apiServis.getAllBids().subscribe((data) => {
      this.listOfBids = <Bid[]>JSON.parse(JSON.stringify(data));
    })
  }
  getUsersRole(): User {
    let user = new User(2, "user", "user", "user", "user", "user", "user", "user", 1)
    return user;
  }

  getNumberOfBidsForProduct(id: number): number {
    let iterator = 0;
    for (let item of this.listOfBids) {
      if (item.productId == id) {
        iterator++;
      }
    }
    return iterator;
  }

  getHighestBidForProduct(id: number): number {
    let max = 0;
    for (let item of this.listOfBids) {
      if (item.productId == id && item.amount >= max ) {
          max = item.amount;
      }
    }
    return max;
  }
}
