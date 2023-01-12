import { Component, ComponentFactoryResolver, ComponentRef, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { AddItemComponent } from '../add-item/add-item.component';
import { BasicComponent } from '../basic/basic.component';

@Component({
  selector: 'app-shipping',
  templateUrl: './shipping.component.html',
  styleUrls: ['./shipping.component.css']
})
export class ShippingComponent implements OnInit {

  @ViewChild('container', { read: ViewContainerRef })
  container!: ViewContainerRef;

  components: ComponentRef<any>[] = [];

  clicked = false;

<<<<<<< HEAD
<<<<<<< HEAD
  model: any = {};

  constructor(private addItemComponent: AddItemComponent, private componentFactoryResolver: ComponentFactoryResolver) { }

  ngOnInit(): void {
    let photoName = this.addItemComponent.model.photo as string;
    photoName = photoName.substring(12, photoName.length);
=======
  constructor(private parentRef: AddItemComponent, private componentFactoryResolver: ComponentFactoryResolver) { }

  ngOnInit(): void {
>>>>>>> daaab420 (Make all three tabs when adding products)
=======
  model: any = {};

  validatePrice!: number;

  validateDate!: number;

  constructor(private addItemComponent: AddItemComponent, private componentFactoryResolver: ComponentFactoryResolver) { }

  ngOnInit(): void {
    let a = this.addItemComponent.model.photo as string;
    a = a.substring(12, a.length);
>>>>>>> 4ca4dd03 (Implement adding products to the database and adding images to Firebase)
  }

  /**
  * The method that is trigged when user clicks on "Back" button and it is calling the
  * method from parent component to remove this one
 */

  remove_me() {
<<<<<<< HEAD
<<<<<<< HEAD
    this.addItemComponent.removeComponent();
=======
    this.parentRef.removeComponent();
>>>>>>> daaab420 (Make all three tabs when adding products)
=======
    this.addItemComponent.removeComponent();
>>>>>>> 4ca4dd03 (Implement adding products to the database and adding images to Firebase)
  }

  /**
  * The method we use to add(display) next component, BasicComponent
  */

  addComponent() {
    if (this.validatePrice == 1 && this.validateDate == 1) {
      let componentClass = BasicComponent;
      const componentFactory = this.componentFactoryResolver.resolveComponentFactory(componentClass);
      const component = this.container.createComponent(componentFactory);
      this.components.push(component);
      this.clicked = true;
    }
    else {
      this.validatePrice == undefined ? this.validatePrice = 0 : null;
      this.validateDate == undefined ? this.validateDate = 0 : null;
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
   * A method that monitors changes when entering the start price
   * @param event when user input some value
   */

  changePrice(event: any) {
    let isnum = /^\d+$/.test(this.model.startPrice);
    isnum == false ? this.validatePrice = 0 : this.validatePrice = 1;
  }

  /**
   * A method that tracks changes when entering a start date and an end date
   * @param event when user input some value
   */

  changeDate(event: any) {
    let firstDate = this.model.startDate as Date;
    let secondDate = this.model.endDate as Date;
    secondDate < firstDate || secondDate == undefined || firstDate == undefined ? this.validateDate = 0 : this.validateDate = 1;
  }
}
