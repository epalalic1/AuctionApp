import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { AppComponent } from 'src/app/app.component';
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

  constructor(private appComponent: AppComponent) { }

  ngAfterViewInit(): void {
    setTimeout(() => {
      let inputElement: HTMLElement = this.activeBtn.nativeElement as HTMLElement;
      inputElement.click();
    });
  }

  ngOnInit(): void {
    if (this.products?.length) {
      this.products = JSON.parse(JSON.stringify(this.products))
      this.products = ProductUtils.productsWithListOfImages(this.products, this.appComponent.listOfProductsImages);
      this.findActiveAndSoldProducts(this.products);
      if (this.bids?.length) {
        this.bids = JSON.parse(JSON.stringify(this.bids));
        this.bids = this.bids.filter((item) => item.userId != this.products[0]?.userId);
      }
    }
  }

  /**
   * A method that filters products based on date into products that can still be bid on and
   *  products that have been sold by the user.
   * @param products a list of all products that the user has put up for sale
   */

  findActiveAndSoldProducts(products: Product[]) {
    products.map((product) => {
      let result = ProductUtils.findTimeLeftForProduct(product).split(" ")[0];
      if (product.status.toString() == 'false') {
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
        this.bids?.length ? this.bids.filter((res) => res.productId == product.id).length : 0,
        this.bids?.length ? ProductUtils.findHighestBid(this.bids.filter((res) => res.productId == product.id)) : 0,
        product.imageName
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
        ProductUtils.findHighestBid(this.bids.filter((res) => res.productId == product.id)),
        product.imageName
      )
      this.finalList.push(item);
    })
  }

  startSelling() {

  }
}
