import { Component, Input, OnInit } from '@angular/core';
import { Category } from '../../models/category';
<<<<<<< HEAD
import { ApiService } from '../../services/api.service';
import { CategoryServiceService } from '../../services/category-service.service';
=======
>>>>>>> 8f14b1d9 (First version of landing page)

@Component({
  selector: 'app-sidemenu',
  templateUrl: './sidemenu.component.html',
  styleUrls: ['./sidemenu.component.css']
})
export class SidemenuComponent implements OnInit {

  @Input()
  allCategories!: Category[];
<<<<<<< HEAD

  constructor(private apiServis: ApiService) { }

  ngOnInit(): void {
    this.apiServis.getAllCategories().subscribe((rez) => {
      this.allCategories = <Category[]>JSON.parse(JSON.stringify(rez));
    })
  }

  onClick(category: Category): void {
    category.isChecked = !category.isChecked;
  }
=======
  counter :number = 0;
  

  constructor(private service:CategoryServiceService) { }

  ngOnInit(): void {

    
    this.allCategories = this.service.findAllCategories();
  }
  onClick(category:Category) :void{
    category.isChecked = !category.isChecked;
  }

>>>>>>> 8f14b1d9 (First version of landing page)
}
