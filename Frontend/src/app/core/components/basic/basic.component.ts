import { Component, OnInit } from '@angular/core';
import { AddItem } from '../../models/add-item';
import { Address } from '../../models/address';
import { User } from '../../models/user';
import { ApiService } from '../../services/api.service';
import { AddItemComponent } from '../add-item/add-item.component';
import { ShippingComponent } from '../shipping/shipping.component';
import { getStorage, ref, uploadBytes } from "firebase/storage";

@Component({
  selector: 'app-basic',
  templateUrl: './basic.component.html',
  styleUrls: ['./basic.component.css']
})
export class BasicComponent implements OnInit {

  address: Address = new Address();

  currentUser: User = new User;

  constructor(
    private shippingComponent: ShippingComponent,
    private apiService: ApiService,
    private addItemComponent: AddItemComponent) { }

  ngOnInit(): void {
    this.apiService.getAddressOfCurrentUser().subscribe((address) => {
      this.address = <Address>JSON.parse(JSON.stringify(address))
    })
    this.apiService.getCurrentUser().subscribe((user) => {
      this.currentUser = <User>JSON.parse(JSON.stringify(user));
    })

  }

  /**
   * The method that is trigged when user clicks on "Back" button and it is calling the
   * method from parent component to remove this one
   */

  remove_me() {
    this.shippingComponent.removeComponent();
  }
  /**
   * The method that is triggered when the user clicks on the next button on the last
   * tab when adding a product.
   * In addition to making a request to the backend to get the product in the database,
   * we also create a call to Firebase to add the corresponding image for that product
   */

  addItem() {
    const storage = getStorage();
    const storageRef = ref(storage, this.addItemComponent.fileToUpload?.name);
    let addItem = new AddItem(
                  this.addItemComponent.model.name,
                  this.addItemComponent.model.category,
                  this.addItemComponent.model.subcategory,
                  this.addItemComponent.model.description,
                  this.addItemComponent.model.photo.toString().substring(12, this.addItemComponent.model.photo.toString().length),
                  this.shippingComponent.model.startPrice,
                  this.shippingComponent.model.startDate,
                  this.shippingComponent.model.endDate,
    )
    this.apiService.addNewProduct(addItem).subscribe((response) => {
      uploadBytes(storageRef, this.addItemComponent.fileToUpload).then((snapshot) => {
        window.alert("Product has been uploaded");
      });
    })
  }
}
