import { Injectable } from '@angular/core';
import { Category } from '../models/category';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class CategoryServiceService {
  a : Category[] = []

  constructor(private apiService:ApiService) { }
}
