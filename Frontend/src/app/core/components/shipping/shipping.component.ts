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
  model: any = {};

  constructor(private addItemComponent: AddItemComponent, private componentFactoryResolver: ComponentFactoryResolver) { }

  ngOnInit(): void {
    let photoName = this.addItemComponent.model.photo as string;
    photoName = photoName.substring(12, photoName.length);
=======
  constructor(private parentRef: AddItemComponent, private componentFactoryResolver: ComponentFactoryResolver) { }

  ngOnInit(): void {
>>>>>>> ba34a843 (Make all three tabs when adding products)
  }

  /**
  * The method that is trigged when user clicks on "Back" button and it is calling the
  * method from parent component to remove this one
 */

  remove_me() {
<<<<<<< HEAD
    this.addItemComponent.removeComponent();
=======
    this.parentRef.removeComponent();
>>>>>>> ba34a843 (Make all three tabs when adding products)
  }

  /**
  * The method we use to add(display) next component, BasicComponent
  */

  addComponent() {
    let componentClass = BasicComponent;
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(componentClass);
    const component = this.container.createComponent(componentFactory);
    this.components.push(component);
    this.clicked = true;
  }

  /**
    * The method we use to remove first component
  */

  removeComponent() {
    this.container.remove(0);
    this.components.splice(0, 1);
    this.clicked = false;
  }
}
