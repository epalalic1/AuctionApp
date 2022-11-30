import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Category } from '../models/category';
import { Product } from '../models/product';
import { User } from '../models/user';
import { Bid } from '../models/bid';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  headers = new HttpHeaders().set('Access-Control-Allow-Origin', '*');
  headersPost = new HttpHeaders().set('Access-Control-Allow-Origin', '*').set(
    'Content-Type', 'application/json'
  );

  firstPartOfUrl : string = 'http://localhost:';
  portUrl : string = '8080/';
  intializeTables = this.firstPartOfUrl + this.portUrl + 'auctionapp/';
  getCategories = this.firstPartOfUrl + this.portUrl +  'auctionapp/category/getAll';
  getSubcategories = this.firstPartOfUrl + this.portUrl + 'subcategory/getAll';
  getLastChanceProducts =  this.firstPartOfUrl + this.portUrl + 'product/getLastChanceProducts';
  getNewProducts = this.firstPartOfUrl + this.portUrl + 'product/getNewProducts';
  getAllBid = this.firstPartOfUrl + this.portUrl + 'bid/getAll';
  addBid = this.firstPartOfUrl + this.portUrl + 'bid/addBid';
  getAllUsers = this.firstPartOfUrl + this.portUrl + 'user/getAll';

  constructor(private http: HttpClient) {}
  
  intializeDatabaseTables(): Observable<any> {
    return this.http.get(this.intializeTables, { 'headers': this.headers, responseType: 'text' })
  }

  getAllCategories():Observable<{categories:Category[]}>{
    return this.http.get<{categories:Category[]}>(this.getCategories, { 'headers': this.headers, responseType: 'json' })
  }
  getLastChanceProduct():Observable<{products:Product[]}>{
    return  this.http.get<{products:Product[]}>(this.getLastChanceProducts, { 'headers': this.headers, responseType: 'json' })
   }

  getNewArrivalsProduct():Observable<{products:Product[]}>{
      return this.http.get<{products:Product[]}>(this.getNewProducts, { 'headers': this.headers, responseType: 'json' })
  }

  getAllBids(): Observable<{ bids: Bid[] }> {
    return this.http.get<{ bids: Bid[] }>(this.getAllBid, { 'headers': this.headers });
  }

  addOneBid(bid: Bid): Observable<any> {
    return this.http.post<any>(this.addBid, JSON.stringify(bid), { 'headers': this.headersPost })
  }

  getUsers(): Observable<{ users: User[] }> {
    return this.http.get<{ users: User[] }>(this.getAllUsers, { 'headers': this.headers })
  }
}
