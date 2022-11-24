import { Injectable } from '@angular/core';
import { Product } from '../models/product';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class NewArrivalsService {

  constructor(private apiService:ApiService) { }

  getNewArrivals():Product[] {
    return this.apiService.getNewArrivalsProduct();
  }
}
