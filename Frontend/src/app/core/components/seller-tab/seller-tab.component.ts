import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { Bid } from '../../models/bid';
import { ItemInTable } from '../../models/item-in-table';
import { Product } from '../../models/product';
import { ProductUtils } from '../../utils/product-utils';

@Component({
  selector: 'app-seller-tab',
  templateUrl: './seller-tab.component.html',
  styleUrls: ['./seller-tab.component.css']
})
export class SellerTabComponent implements OnInit {

  @Input()
  products!: Product[]

  @Input()
  bids!: Bid[];

  finalList: ItemInTable[] = []

  activeProducts: Product[] = []

  soldProducts: Product[] = []

  @ViewChild('activeBtn')
  activeBtn!: ElementRef;

  constructor() { }

  ngAfterViewInit(): void {
    setTimeout(() => {
      let inputElement: HTMLElement = this.activeBtn.nativeElement as HTMLElement;
      inputElement.click();
    });
  }

  ngOnInit(): void {
    this.products = JSON.parse(JSON.stringify(this.products))
    this.bids = JSON.parse(JSON.stringify(this.bids));
    this.bids = this.bids.filter((item) => item.userId != this.products[0]?.userId);
    this.findActiveAndSoldProducts(this.products);
  }

  /**
   * A method that filters products based on date into products that can still be bid on and
   *  products that have been sold by the user.
   * @param products a list of all products that the user has put up for sale 
   */

  findActiveAndSoldProducts(products: Product[]) {
    products.map((product) => {
      let result = ProductUtils.findTimeLeftForProduct(product).split(" ")[0];
      if (Number(result) > 0 && product.status.toString() == 'false') {
        this.activeProducts.push(product);
      }
      else {
        this.soldProducts.push(product);
      }
    })
  }

  /**
   * The method that triggers when the user clicks on the 'Active' button, and in the final list 
   * that we display, we put products that can still be bid on
   */

  activeProductsBtn() {
    this.finalList.splice(0);
    this.activeProducts.map((product) => {
      let item = new ItemInTable(
        product.name,
        ProductUtils.findTimeLeftForProduct(product),
        product.startPrice,
        this.bids.filter((res) => res.productId == product.id).length,
        ProductUtils.findHighestBid(this.bids.filter((res) => res.productId == product.id))
      )
      this.finalList.push(item);
    })
  }

  /**
   * The method that triggers when the user clicks on the 'Sold' button, and in the final 
   * list we display we put the products that have sold
   */

  soldProductsBtn() {
    this.finalList.splice(0);
    this.soldProducts.map((product) => {
      let item = new ItemInTable(
        product.name,
        ProductUtils.findTimeLeftForProduct(product),
        product.startPrice,
        this.bids.filter((res) => res.productId == product.id).length,
        ProductUtils.findHighestBid(this.bids.filter((res) => res.productId == product.id))
      )
      this.finalList.push(item);
    })
  }
}
