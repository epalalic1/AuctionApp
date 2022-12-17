import { Injectable } from '@angular/core';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService{

  currentUser!: string;

  constructor() { }

  setUser(user: string) {
    this.currentUser = user;
  }

  getUser(): string {
    return this.currentUser;
  }
}
