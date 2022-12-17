import { Injectable } from '@angular/core';
import { Product } from '../models/product';
import { ProductUtils } from '../utils/product-utils';

@Injectable({
  providedIn: 'root'
})
export class ShopService {

  constructor() { }

  /**
   * Method findDidYouMeanProduct returns the name of the product that the user might be looking for
   * @param products is the first parameter of this method and it is an array of Products 
   * @param search is a string entered by the user and we use it to compare with the product names 
   * that are in the database
   * @returns the name of the product that is most similar to the string entered by the user for the search
   */

  findDidYouMeanProduct(products: Product[], search: string): string {
    let filteredProducts = products.filter((item) => ProductUtils.compareTwoStrings(item.name, search) >= 0.7);
    filteredProducts = filteredProducts.sort((a, b) => {
      if (ProductUtils.compareTwoStrings(a.name, search) > ProductUtils.compareTwoStrings(b.name, search)) {
        return -1;
      }
      return 1;
    });
    return filteredProducts[0].name;
  }
}
