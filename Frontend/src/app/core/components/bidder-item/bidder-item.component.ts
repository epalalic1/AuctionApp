import { Component, Input, OnInit } from '@angular/core';
import { BidderForProduct } from '../../models/bidder-for-product';

@Component({
  selector: 'app-bidder-item',
  templateUrl: './bidder-item.component.html',
  styleUrls: ['./bidder-item.component.css']
})
export class BidderItemComponent implements OnInit {

  @Input()
  bidderForProduct!: BidderForProduct;

  date: String = ""

  constructor() { }

  ngOnInit(): void {
    this.date = this.bidderForProduct.dateOfBid.toString();
    this.date = this.date.slice(0, 12);
    this.date = this.date.slice(0, 10);
  }
}
