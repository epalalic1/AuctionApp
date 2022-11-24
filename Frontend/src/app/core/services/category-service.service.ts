import { Injectable } from '@angular/core';
import { Category } from '../models/category';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class CategoryServiceService {

  constructor(private apiService:ApiService) { }
   findAllCategories() : Category[] {
    return this.apiService.getAllCategories();
      
  }
}
