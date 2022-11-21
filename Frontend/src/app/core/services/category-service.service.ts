import { Injectable } from '@angular/core';
import { Category } from '../models/category';
import { InitializeService } from './initialize.service';

@Injectable({
  providedIn: 'root'
})
export class CategoryServiceService {

  constructor(private servis:InitializeService) { }
   findAllCategories() : Category[] {
    return this.servis.getAllCategories();
      
  }
}
