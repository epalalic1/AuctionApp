import { Component, OnInit } from '@angular/core';
import { AddItem } from '../../models/add-item';
import { Address } from '../../models/address';
import { User } from '../../models/user';
import { ApiService } from '../../services/api.service';
import { AddItemComponent } from '../add-item/add-item.component';
import { ShippingComponent } from '../shipping/shipping.component';
import { getStorage, ref, uploadBytes } from "firebase/storage";
import { Validation } from '../../utils/validation';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-basic',
  templateUrl: './basic.component.html',
  styleUrls: ['./basic.component.css']
})
export class BasicComponent implements OnInit {

  address: any = new Address();

  currentUser: User = new User;

  validateAddress!: number;

  validateEmail!: number;

  validateCity!: number;

  validateZipCode!: number;

  validateCountry!: number;

  validatePhoneNumber: number = 0;

  model: any = {}

  constructor(
    private shippingComponent: ShippingComponent,
    private apiService: ApiService,
    private addItemComponent: AddItemComponent) { })

  ngOnInit(): void {
    this.apiService.getAddressOfCurrentUser().subscribe((address) => {
      this.address = <Address>JSON.parse(JSON.stringify(address));
      if (this.address != null) {

      }
    })
    this.apiService.getCurrentUser().subscribe((user) => {
      this.currentUser = <User>JSON.parse(JSON.stringify(user));
      this.validateEmail = 1;
    })

  }
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
    if (this.validateAddress == 1 && this.validateEmail == 1 && this.validateCity == 1
      && this.validateZipCode == 1 && this.validateCountry == 1 && this.validatePhoneNumber) {
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
          Swal.fire('Hi', 'You have successfully added a product!', 'success').then(() => {
            window.location.reload();
          });
        });
      })
    }
    else {
      this.validateAddress == undefined ? this.validateAddress = 0 : null;
      this.validateEmail == undefined ? this.validateEmail = 0 : null;
      this.validateCity == undefined ? this.validateCity = 0 : null;
      this.validateZipCode == undefined ? this.validateZipCode = 0 : null;
      this.validateCountry == undefined ? this.validateCountry = 0 : null;
      this.validatePhoneNumber == undefined ? this.validatePhoneNumber = 0 : null;
    }
  }

  cancel() {
    window.location.reload()
  }

  /**
   * Method that tracks changes when changing an address
   * @param event when user input some value
   */

  changeAddress(event: any) {
    this.model.address == undefined || this.model.address == "" ? this.validateAddress = 0 : this.validateAddress = 1;
  }

  /**
   * Method that tracks changes when changing an email
   * @param event when user input some value
   */

  changeEmail(event: any) {
    this.model.email == undefined || !Validation.checkIfEmailIsValid(this.model.email) || this.model.email == "" ? this.validateEmail = 0 : this.validateEmail = 1;
  }

  /**
   * Method that tracks changes when entering a city
   * @param event when user input some value
   */

  changeCity(event: any) {
    this.model.city == undefined || this.model.city == "" ? this.validateCity = 0 : this.validateCity = 1;
  }

  /**
   * Method that tracks changes when entering a zip code
   * @param event when user input some value
   */

  changeZipCode(event: any) {
    this.model.zipCode == undefined || this.model.zipCode == "" ? this.validateZipCode = 0 : this.validateZipCode = 1;
  }

  /**
   * Method that tracks changes when entering a phone number
   * @param event when user input some value
   */

  changePhone(event: any) {
    !Validation.checkIfPhoneIsValid(this.model.phone) ? this.validatePhoneNumber = 0 : this.validatePhoneNumber = 1;
  }

  /**
   * Method that keeps track of whether one of the countries is selected
   */

  onSelectedCountry() {
    this.validateCountry = 1;
  }
}
