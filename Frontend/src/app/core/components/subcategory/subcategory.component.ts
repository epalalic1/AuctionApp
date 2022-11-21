import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-subcategory',
  templateUrl: './subcategory.component.html',
  styleUrls: ['./subcategory.component.css']
})
export class SubcategoryComponent implements OnInit {

  constructor() { }
  subcategories = new Set<String>();

  @Input()
  allCategories!: string[];

  ngOnInit(): void {
  }
  toggleEditable(event:any,subcategory:string) {
    if ( event.target.checked ) {
       this.subcategories.add(subcategory);
   }
   else {
      this.subcategories.delete(subcategory);
   }
}
}
