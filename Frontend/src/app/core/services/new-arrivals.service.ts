import { Injectable } from '@angular/core';
import { Product } from '../models/product';
import { InitializeService } from './initialize.service';

@Injectable({
  providedIn: 'root'
})
export class NewArrivalsService {

  constructor(private servis:InitializeService) { }

  getNewArrivals():Product[] {
    return this.servis.getNewArrivalsProduct();
  }
}
