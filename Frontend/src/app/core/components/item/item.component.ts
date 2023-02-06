import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { getStorage, ref, getDownloadURL } from "firebase/storage";
import { Product } from '../../models/product';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent implements OnInit {

  @Input()
  product!: Product

  image: string[] = [];

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  onClick(product1: Product): void {
    this.router.navigate(['/Product', { id: this.product.id }])
  }
}
