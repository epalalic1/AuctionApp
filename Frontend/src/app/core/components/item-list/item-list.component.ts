import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Bid } from '../../models/bid';
import { Product } from '../../models/product';
import { ApiService } from '../../services/api.service';
import { ProductUtils } from '../../utils/product-utils';
import { getStorage, ref, getDownloadURL } from "firebase/storage";

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {

  @Input()
  product!: Product;

  highestBid: number = 0;

  image: string[] = [];

  constructor(private apiService: ApiService, private router: Router) { }

  ngOnInit(): void {
    this.apiService.getAllBids().subscribe((bids) => {
      let allBids = <Bid[]>JSON.parse(JSON.stringify(bids));
      if (allBids?.length) {
        let productBid = allBids.filter(item => item.productId == this.product.id);
        if (productBid?.length) {
          this.highestBid = ProductUtils.findHighestBid(productBid);
        }
      }
    })
  }

  onClick(): void {
    this.router.navigate(['/Product', { id: this.product?.id }])
  }
}
