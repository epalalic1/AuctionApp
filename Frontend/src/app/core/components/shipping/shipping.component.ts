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

  constructor(private parentRef: AddItemComponent, private componentFactoryResolver: ComponentFactoryResolver) { }

  ngOnInit(): void {
  }

  /**
  * The method that is trigged when user clicks on "Back" button and it is calling the
  * method from parent component to remove this one
 */

  remove_me() {
    this.parentRef.removeComponent();
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
