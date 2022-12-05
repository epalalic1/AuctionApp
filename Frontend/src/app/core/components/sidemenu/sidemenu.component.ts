import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Category } from '../../models/category';
import { Product } from '../../models/product';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-sidemenu',
  templateUrl: './sidemenu.component.html',
  styleUrls: ['./sidemenu.component.css']
})
export class SidemenuComponent implements OnInit {

  @Input()
  allCategories!: Category[];

  @Output() list = new EventEmitter<Product[]>();

  listOfProducts:Product[] = [];

  constructor(private apiServis: ApiService) { }

  ngOnInit(): void {
    this.apiServis.getAllCategories().subscribe((rez) => {
      this.allCategories = <Category[]>JSON.parse(JSON.stringify(rez));
    })
  }

  onClick(category: Category): void {
    category.isChecked = !category.isChecked;
    this.listOfProducts.splice(0);
    this.apiServis.getAllProducts().subscribe((rez)=>{
        let list1  = <Product[]> JSON.parse(JSON.stringify(rez));
        for (let item of list1) {
            if (item.categoryId == category.id) {
                this.listOfProducts.push(item);
            }
        }
        this.list.emit(this.listOfProducts);
    })
  }
}
