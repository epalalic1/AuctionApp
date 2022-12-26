import { Component, ComponentFactoryResolver, ComponentRef, Input, OnInit, SimpleChanges, Type, ViewChild, ViewContainerRef } from '@angular/core';
import { threadId } from 'worker_threads';
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

  validateNameOfProduct !: number;

  validateCategory !: number;

  validateSubcategory !: number;

  validateDescription !: number;

  validatePicture!: number;

  pictureName = "";

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
    if (this.validateNameOfProduct == 1 && this.validateCategory == 1 && this.validateSubcategory == 1
      && this.validateDescription == 1 && this.validatePicture == 1) {
      let componentClass = ShippingComponent;
      const componentFactory = this.componentFactoryResolver.resolveComponentFactory(componentClass);
      const component = this.container.createComponent(componentFactory);
      this.components.push(component);
      this.clicked = true;
    }
    else {
      this.validateNameOfProduct == undefined ? this.validateNameOfProduct = 0 : null;
      this.validateCategory == undefined ? this.validateCategory = 0 : null;
      this.validateSubcategory == undefined ? this.validateSubcategory = 0 : null;
      this.validateDescription == undefined ? this.validateDescription = 0 : null;
      this.validatePicture == undefined ? this.validatePicture = 0 : null;
    }
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
    this.validatePicture = 1;
    this.pictureName = this.fileToUpload.name;
  }

  /**
   * A method that tracks changes when entering a product name
   * @param event when user input some value
   */

  changeName(event: any) {
    this.model.name == undefined || this.model.name == "" ? this.validateNameOfProduct = 0 : this.validateNameOfProduct = 1;
  }

  /**
   * A method that tracks changes when entering a product description
   * @param event when user input some value
   */

  changeDescription(event: any) {
    this.model.description == undefined || this.model.description == "" || this.model.description.length > 700 ? this.validateDescription = 0 : this.validateDescription = 1;
  }

  /**
   * A method that monitors changes in the selection of one category
   */

  onSelectedCategory() {
    this.validateCategory = 1;
  }

  /**
   * A method that monitors changes in the selection of one subcategory
   */

  onSelectedSubcategory() {
    this.validateSubcategory = 1;
  }

  cancel() {
    window.location.reload();
  }
}
