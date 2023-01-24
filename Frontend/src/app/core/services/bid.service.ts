import { Injectable } from '@angular/core';
import { AuthGuard } from '../guards/auth.guard';
import { Bid } from '../models/bid';
import { User } from '../models/user';
import { ApiService } from './api.service';


@Injectable({
  providedIn: 'root'
})
export class BidService {

  listOfBids: Bid[] = [];

  constructor(private apiServis: ApiService, private authGuard: AuthGuard) {
  }

  getBids(): void {
    if (this.authGuard.canActivate()) {
      this.apiServis.getAllBids().subscribe((data) => {
        this.listOfBids = <Bid[]>JSON.parse(JSON.stringify(data));
      })
    }
  }

  getNumberOfBidsForProduct(id: number): number {
    let iterator = 0;
    if (this.listOfBids?.length) {
      for (let item of this.listOfBids) {
        if (item.productId == id) {
          iterator++;
        }
      }
    }
    return iterator;
  }

  getHighestBidForProduct(id: number): number {
    let max = 0;
    if (this.listOfBids?.length) {
      for (let item of this.listOfBids) {
        if (item.productId == id && item.amount >= max) {
          max = item.amount;
        }
      }
    }

    return max;
  }
}
