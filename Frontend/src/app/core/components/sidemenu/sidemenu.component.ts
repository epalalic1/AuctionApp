import { Component, Input, OnInit } from '@angular/core';
import { Category } from '../../models/category';
import { CategoryServiceService } from '../../services/category-service.service';

@Component({
  selector: 'app-sidemenu',
  templateUrl: './sidemenu.component.html',
  styleUrls: ['./sidemenu.component.css']
})
export class SidemenuComponent implements OnInit {

  @Input()
  allCategories!: Category[];

  constructor(private categoryService:CategoryServiceService) { }

  ngOnInit(): void {
    this.allCategories = this.categoryService.findAllCategories();
  }

  onClick(category:Category) :void{
    category.isChecked = !category.isChecked;
  }
}
