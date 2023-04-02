import { Component, Input, OnInit } from '@angular/core';
import { ProductImages } from '../../models/product-images';

@Component({
  selector: 'app-recommended-tab',
  templateUrl: './recommended-tab.component.html',
  styleUrls: ['./recommended-tab.component.css']
})
export class RecommendedTabComponent implements OnInit {

  @Input()
  listOfProductsImages: ProductImages[] = [];

  constructor() { }

  ngOnInit(): void {
  }

}
