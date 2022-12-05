import { Component, OnInit } from '@angular/core';
import { Product } from '../../models/product';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {

  list: Product[] = []

  clicked: boolean = false;

  iterator: number = 0;

  list1: Product[] = [];

  end: boolean = false;

  constructor() { }

  ngOnInit(): void {
    this.end = true;
  }

  getList(ev: Product[]) {
    this.list = ev;
    this.list1.splice(0);
    this.iterator = 0;
    if (this.list.length < 9) {
      this.clicked = false;
      this.end = true;
    }
  }

  onClick(): void {
    this.iterator = this.iterator + 1;
    this.clicked = true;
    let start = this.iterator * 9;
    for (let i = start; i < start + 9; i++) {
      if (i == this.list.length) {
        this.end = false;
        break;
      }
      this.list1.push(this.list[i]);
    }
  }

}
