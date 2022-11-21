import { Component, Input, OnInit } from '@angular/core';
import { Product } from '../../models/product';

@Component({
  selector: 'app-new-arrivals',
  templateUrl: './new-arrivals.component.html',
  styleUrls: ['./new-arrivals.component.css']
})
export class NewArrivalsComponent implements OnInit {

  @Input()
  newArrivals!:Product[];

  constructor(private service:NewArrivalsService) { }

  ngOnInit(): void {
    this.newArrivals = this.service.getNewArrivals();
  }
}
