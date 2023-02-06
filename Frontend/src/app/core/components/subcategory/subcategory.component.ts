import { Component, EventEmitter, Injectable, Input, OnInit, Output } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { Product } from '../../models/product';
import { Subcategory } from '../../models/subcategory';

@Component({
  selector: 'app-subcategory',
  templateUrl: './subcategory.component.html',
  styleUrls: ['./subcategory.component.css']
})

@Injectable({
  providedIn: 'root'
})

export class SubcategoryComponent implements OnInit {

  subcategories = new Set<String>();

  @Output() list = new EventEmitter<Set<String>>();

  @Input()
  allSubcategories!: string[];

  numberOfProducts: number[] = [];

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.findProductsForEachSubcategory();
  }

  toggleEditable(event: any, subcategory: string) {
    if (event.target.checked) {
      this.subcategories.add(subcategory);
    }
    else {
      this.subcategories.delete(subcategory);
    }
    this.list.emit(this.subcategories);
  }

  /**
   * The method we use to find the number of products for each of the subcategories
   */

  findProductsForEachSubcategory() {
    this.apiService.getAllProducts().subscribe((allProducts) => {
      let listOfAllProducts = <Product[]>JSON.parse(JSON.stringify(allProducts));
      this.apiService.getAllSubcategories().subscribe((allSubcategories) => {
        let listOfAllSubcategories = <Subcategory[]>JSON.parse(JSON.stringify(allSubcategories));
        this.allSubcategories.map((nameOfSubcategory) => {
          let subcategory = listOfAllSubcategories.find(subcategory => subcategory.name === nameOfSubcategory);
          let productOfSubcategory = listOfAllProducts.filter((product) => product.subcategoryId == subcategory?.id);
          this.numberOfProducts.push(productOfSubcategory.length);
        })
      })
    })
  }
}
