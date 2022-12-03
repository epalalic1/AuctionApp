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

  image:string[] = [];

  constructor(private router: Router) { }

  ngOnInit(): void {
    const storage = getStorage();
    for (const img of this.product.imageName) {
      getDownloadURL(ref(storage, img))
        .then((url) => {
          this.image.push(url);
        })
    }
    this.product.imageName = this.image;
  }
  
  onClick(product1: Product): void {
    this.router.navigate(['Product'], { queryParams: JSON.parse(JSON.stringify(product1)), skipLocationChange: true });
    console.log(product1);
  }
}
