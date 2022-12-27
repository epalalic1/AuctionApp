import { Component, Input, OnInit } from '@angular/core';
import { Product } from '../../models/product';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-new-arrivals',
  templateUrl: './new-arrivals.component.html',
  styleUrls: ['./new-arrivals.component.css']
})
export class NewArrivalsComponent implements OnInit {

  @Input()
  newArrivals!: Product[];

  constructor(private apiServis: ApiService) { }

  ngOnInit(): void {
    this.apiServis.getNewArrivalsProduct().subscribe((rez) => {
      let products = <Product[]>JSON.parse(JSON.stringify(rez));
      this.newArrivals = products.filter(item => item.status.toString() == 'false');
    })
  }
}
