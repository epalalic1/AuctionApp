import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Bid } from '../../models/bid';
import { Product } from '../../models/product';
import { ApiService } from '../../services/api.service';
import { ProductUtils } from '../../utils/product-utils';
<<<<<<< HEAD
import { getStorage, ref, getDownloadURL } from "firebase/storage";
=======
>>>>>>> a364e4cf (Add buttons for different preview and add new component)

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {

  @Input()
<<<<<<< HEAD
  product!: Product;

  highestBid: number = 0;

  image: string[] = [];

  constructor(private apiService: ApiService, private router: Router) { }

  ngOnInit(): void {
    const storage = getStorage();
    for (const img of this.product.imageName) {
      getDownloadURL(ref(storage, img))
        .then((url) => {
          this.image.push(url);
        })
    }
    this.product.imageName = this.image;
    this.apiService.getAllBids().subscribe((bids) => {
      let allBids = <Bid[]>JSON.parse(JSON.stringify(bids));
      let productBid = allBids.filter(item => item.productId == this.product.id);
      if (productBid.length != 0) {
        this.highestBid = ProductUtils.findHighestBid(productBid);
      }
=======
  product!:Product;

  highestBid:number=0;

  constructor(private apiService:ApiService,private router: Router) { }

  ngOnInit(): void {
    this.apiService.getAllBids().subscribe((bids) => {
        let allBids = <Bid[]> JSON.parse(JSON.stringify(bids));
        let productBid = allBids.filter(item => item.productId == this.product.id);
        if (productBid.length!=0) {
          this.highestBid = ProductUtils.findHighestBid(productBid);
        }
>>>>>>> a364e4cf (Add buttons for different preview and add new component)
    })
  }

  onClick(product1: Product): void {
    this.router.navigate(['/Product', { id: this.product.id }])
  }

}
