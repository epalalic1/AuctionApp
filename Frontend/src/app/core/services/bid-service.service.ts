import { Injectable } from '@angular/core';
import { Bid } from '../models/bid';
import { User } from '../models/user';
import { InitializeService } from './initialize.service';

@Injectable({
  providedIn: 'root'
})
export class BidServiceService {

 

  listOfBids: Bid[] = [];
  constructor(private servis:InitializeService) { 
  }

  getBids():void {
    this.servis.getAllBids().subscribe((data) => {
      let a = (JSON.parse(JSON.stringify(data)));
      for(let b of Object.values(a)) {
        let c = (JSON.parse(JSON.stringify(b)));
        let help = new Bid(c.id,c.amount,c.dateOfBid, c.productId,c.userId);
        this.listOfBids.push(help);
      }
    })
  }
  getUsersRole() :User{
    let user = new User(2,"user","user","user","user","user","user","user", 2)
    return user;
    // 1 - Admin
    // 2 - LogedIn 
    // 3 - Not loged in
  }
  
  getNumberOfBidsForProduct(id:number): number {
    let iterator = 0;

    for (let a of this.listOfBids) {
      
      if (a.productId == id){
        iterator++;
      }
    }
    return iterator;
  }

  getHighestBidForProduct(id:number) :number {
    let max = 0;
    for (let a of this.listOfBids) {
      if (a.productId == id) {
        if (a.amount >= max) {
          max = a.amount;
        }
      }
    }
    return max;
  }
}
