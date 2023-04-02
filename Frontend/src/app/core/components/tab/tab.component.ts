import { Component, Input, OnInit } from '@angular/core';
import { ProductImages } from '../../models/product-images';

@Component({
  selector: 'app-tab',
  templateUrl: './tab.component.html',
  styleUrls: ['./tab.component.css']
})
export class TabComponent implements OnInit {

  @Input()
  listOfProductsImages: ProductImages[] = [];

  constructor() { }

  ngOnInit(): void {
  }
}
