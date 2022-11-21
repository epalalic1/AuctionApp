import { Injectable } from '@angular/core';
import { Product } from '../models/product';
import { InitializeService } from './initialize.service';

@Injectable({
  providedIn: 'root'
})
export class LastChanceService {

  constructor(private servis:InitializeService) { }

  getLastChanceProducts():Product[] {
    return this.servis.getLastChanceProduct();
  
}
}
