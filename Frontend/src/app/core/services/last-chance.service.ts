import { Injectable } from '@angular/core';
import { Product } from '../models/product';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class LastChanceService {

  constructor(private apiService:ApiService) { }

  getLastChanceProducts():Product[] {
    return this.apiService.getLastChanceProduct();
  
}
}
