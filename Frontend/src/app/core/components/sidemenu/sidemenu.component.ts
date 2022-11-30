import { Component, Input, OnInit } from '@angular/core';
import { Category } from '../../models/category';
import { ApiService } from '../../services/api.service';
import { CategoryServiceService } from '../../services/category-service.service';

@Component({
  selector: 'app-sidemenu',
  templateUrl: './sidemenu.component.html',
  styleUrls: ['./sidemenu.component.css']
})
export class SidemenuComponent implements OnInit {

  @Input()
  allCategories!: Category[];

  constructor(private apiServis:ApiService) { }

  ngOnInit(): void {
    this.apiServis.getAllCategories().subscribe((rez)=>{
      this.allCategories = <Category[]> JSON.parse(JSON.stringify(rez));
    })
  }

  onClick(category:Category) :void{
    category.isChecked = !category.isChecked;
  }
}
