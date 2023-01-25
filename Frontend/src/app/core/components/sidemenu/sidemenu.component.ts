import { Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { Category } from '../../models/category';
import { Product } from '../../models/product';
import { Subcategory } from '../../models/subcategory';
import { ApiService } from '../../services/api.service';
import { NewArrivalsService } from '../../services/new-arrivals.service';
import { ItemComponent } from '../item/item.component';
import { ShopComponent } from '../shop/shop.component';

@Component({
  selector: 'app-sidemenu',
  templateUrl: './sidemenu.component.html',
  styleUrls: ['./sidemenu.component.css']
})
export class SidemenuComponent implements OnInit {

  @ViewChild('categoryBtn') myDiv!: ElementRef<HTMLElement>;

  @Input()
  allCategories!: Category[];

  @Output() list = new EventEmitter<Product[]>();

  @Output() listOfFilters = new EventEmitter<Set<String>>();

  listOfProducts: Product[] = [];

  button: string = "";

  constructor(
    private apiServis: ApiService,
    private newArriwals: NewArrivalsService,
    private shopComponent: ShopComponent) { }

  ngOnInit(): void {
    this.apiServis.getAllCategories().subscribe((rez) => {
      this.allCategories = <Category[]>JSON.parse(JSON.stringify(rez));
    })
  }

  onClickCategory(category: Category, i: number): void {
    this.button = document.getElementsByClassName('signCategoryBtn')[i]!.innerHTML;
    category.isChecked = !category.isChecked;
    this.removeFilters();
    if (this.button === "+") {
      this.closeOtherCateories(i);
      document.getElementsByClassName('signCategoryBtn')[i]!.innerHTML = "-";
      this.listOfProducts.splice(0);
      this.apiServis.getAllProducts().subscribe((rez) => {
        let list1 = <Product[]>JSON.parse(JSON.stringify(rez));
        for (let item of list1) {
          if (item.categoryId == category.id) {
            this.listOfProducts.push(item);
          }
        }
        this.list.emit(this.listOfProducts);
      })
    }
    else {
      document.getElementsByClassName('signCategoryBtn')[i]!.innerHTML = "+";
    }
  }

  removeFilters() {
    const checkboxes: Element[] = Array.from(document.getElementsByClassName("checkboxes"));
    checkboxes.map(item => {
      let checkbox = item as HTMLInputElement;
      checkbox.checked = false;
    })
    this.shopComponent.removePriceRangeFilter();
    const subCategoryFilters: Element[] = Array.from(document.getElementsByClassName("subcategoryFilterBtns"));
    for (let i = 0; i < subCategoryFilters.length; i++) {
      this.shopComponent.removeSubcategoryFilter(i);
    }
  }

  closeOtherCateories(i: number) {
    const btns: Element[] = Array.from(document.getElementsByClassName("signCategoryBtn"));
    for (let j = 0; j < btns.length; j++) {
      if (i != j && btns[j]!.innerHTML == "-") {
        document.getElementsByClassName('signCategoryBtn')[j]!.innerHTML = "+";
        this.allCategories[j].isChecked = !this.allCategories[j].isChecked;
      }
    }
  }

  /**
   * The method we use when we want to filter products based on subcategories
   */

  getList(ev: Set<String>) {
    this.apiServis.getAllProducts().subscribe((products) => {
      this.listOfProducts.splice(0);
      let allProucts = <Product[]>JSON.parse(JSON.stringify(products));
      this.apiServis.getAllSubcategories().subscribe((subcategories) => {
        let allSubcategories = <Subcategory[]>JSON.parse(JSON.stringify(subcategories))
        let listOfSubcategories = allSubcategories.filter(item => ev.has(item.name));
        allProucts.map((item) => {
          if (listOfSubcategories.find(subc => subc.id == item.subcategoryId) != undefined) {
            this.listOfProducts.push(item);
          }
        })
        this.list.emit(this.listOfProducts);
        this.listOfFilters.emit(ev);
      })
    })
  }
}
