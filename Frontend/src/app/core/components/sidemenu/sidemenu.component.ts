import { Component, Input, OnInit } from '@angular/core';
import { Category } from '../../models/category';

@Component({
  selector: 'app-sidemenu',
  templateUrl: './sidemenu.component.html',
  styleUrls: ['./sidemenu.component.css']
})
export class SidemenuComponent implements OnInit {

  @Input()
  allCategories!: Category[];
  counter :number = 0;
  

  constructor(private service:CategoryServiceService) { }

  ngOnInit(): void {
    this.allCategories = this.service.findAllCategories();
  }
  
  onClick(category:Category) :void{
    category.isChecked = !category.isChecked;
  }

}
