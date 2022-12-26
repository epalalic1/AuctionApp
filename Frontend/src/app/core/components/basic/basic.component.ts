import { Component, OnInit } from '@angular/core';
import { Address } from '../../models/address';
import { User } from '../../models/user';
import { ApiService } from '../../services/api.service';
import { ShippingComponent } from '../shipping/shipping.component';

@Component({
  selector: 'app-basic',
  templateUrl: './basic.component.html',
  styleUrls: ['./basic.component.css']
})
export class BasicComponent implements OnInit {

  address: Address = new Address();

  currentUser: User = new User;

  constructor(private parentRef: ShippingComponent, private apiService: ApiService) { }

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
    this.parentRef.removeComponent();
  }
}
