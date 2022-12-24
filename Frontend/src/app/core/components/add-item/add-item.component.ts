import { Component, ComponentFactoryResolver, ComponentRef, Input, OnInit, Type, ViewChild, ViewContainerRef } from '@angular/core';
import { Category } from '../../models/category';
import { Subcategory } from '../../models/subcategory';
import { ApiService } from '../../services/api.service';
import { ShippingComponent } from '../shipping/shipping.component';


@Component({
  selector: 'app-add-item',
  templateUrl: './add-item.component.html',
  styleUrls: ['./add-item.component.css']
})


export class AddItemComponent implements OnInit {

  @ViewChild('container', { read: ViewContainerRef })
  container!: ViewContainerRef;

  components: ComponentRef<any>[] = [];

  listOfNameCategories: string[] = [];

  listOfNameSubcategories: string[] = [];

  clicked = false;

  model: any = {}

  fileToUpload: any;
  fileBlob: any;

  constructor(private componentFactoryResolver: ComponentFactoryResolver, private apiService: ApiService) {
  }


  ngOnInit(): void {
    this.apiService.getAllCategories().subscribe((categories) => {
      let listOfCategories = <Category[]>JSON.parse(JSON.stringify(categories));
      this.listOfNameCategories = listOfCategories.map(function (item) {
        return item.name;
      });
    })
    this.apiService.getAllSubcategories().subscribe((subcategories) => {
      let listOfSubcategories = <Subcategory[]>JSON.parse(JSON.stringify(subcategories));
      this.listOfNameSubcategories = listOfSubcategories.map(function (item) {
        return item.name
      })
    })
  }

  /**
   * The method we use to add(display) next component, Shipping Component
   */

  addComponent() {
    let componentClass = ShippingComponent;
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(componentClass);
    const component = this.container.createComponent(componentFactory);
    this.components.push(component);
    this.clicked = true;
    console.log(this.model.name + "  " + this.model.description + "*******");
  }

  /**
  * The method we use to remove first component
  */

  removeComponent() {
    this.container.remove(0);
    this.components.splice(0, 1);
    this.clicked = false;
  }

  /**
   * The method we use to store file that is uploaded
   * @param files list of uploaded
   */

  handleFileInput(files?: FileList) {
    this.fileToUpload = files?.item(0);
  }
}
