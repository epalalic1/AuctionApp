import { Component, Input, OnInit } from '@angular/core';
import { AppComponent } from 'src/app/app.component';
import { Bid } from '../../models/bid';
import { ItemInTable } from '../../models/item-in-table';
import { Product } from '../../models/product';
import { ProductUtils } from '../../utils/product-utils';

@Component({
  selector: 'app-bid-tab',
  templateUrl: './bid-tab.component.html',
  styleUrls: ['./bid-tab.component.css']
})
export class BidTabComponent implements OnInit {

  @Input()
  products!: Product[]

  @Input()
  bids!: Bid[]

  @Input()
  allBids!: Bid[]

  finalList: ItemInTable[] = []

  constructor(private appComponent: AppComponent) { }

  ngOnInit(): void {
<<<<<<< HEAD
    if (this.products?.length) {
      this.products = JSON.parse(JSON.stringify(this.products));
      this.products = ProductUtils.productsWithListOfImages(this.products, this.appComponent.listOfProductsImages);
      this.bids = JSON.parse(JSON.stringify(this.bids));
      this.bids?.length ? this.bids.map((bid) => {
        let filteredBids = this.filterBids(this.bids, bid);
        let item = new ItemInTable(
          this.findProductById(bid.productId)?.name,
          ProductUtils.findTimeLeftForProductWithId(bid.productId, this.products),
          bid.amount,
          filteredBids.length,
          ProductUtils.findHighestBid(filteredBids)
        )
        this.finalList.push(item);
      }) : null
    }

=======
    this.products = JSON.parse(JSON.stringify(this.products));
    this.products = ProductUtils.productsWithListOfImages(this.products, this.appComponent.listOfProductsImages);
    this.bids = JSON.parse(JSON.stringify(this.bids));
    this.bids.map((bid) => {
      let filteredBids = this.filterBids(this.bids, bid);
      let item = new ItemInTable(
        this.findProductById(bid.productId)?.name,
        ProductUtils.findTimeLeftForProductWithId(bid.productId, this.products),
        bid.amount,
        filteredBids.length,
        ProductUtils.findHighestBid(filteredBids)
      )
      this.finalList.push(item);
    })
>>>>>>> d8fcbec2 (Changes)
  }

  /**
   * A method that returnsthe product based on the id
   * @param id of the product we want to find
   * @returns  product
   */

  findProductById(id: number) {
    return this.products.find(product => product.id == id);
  }

  /**
   * A method that lists filtered bids whose product id matches the bid we sent as a parameter
   * @param bids list of bids
   * @param bid we compare with all other bids
   * @returns list of filtered bids
   */

  filterBids(bids: Bid[], bid: Bid) {
    return bids.filter((res) => res.productId == bid.productId)
  }
}
