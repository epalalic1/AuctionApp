import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Category } from '../models/category';
import { Product } from '../models/product';
import { User } from '../models/user';
import { Bid } from '../models/bid';
import { LoginRequest } from '../models/login-request';
import { AuthResponse } from '../models/auth-response';
import { RegisterRequest } from '../models/register-request';
import { UpdateUser } from '../models/update-user';
import { PaymentRequest } from '../models/payment-request';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  headers = new HttpHeaders().set('Access-Control-Allow-Origin', '*');
  headersPost = new HttpHeaders().set('Access-Control-Allow-Origin', '*').set(
    'Content-Type', 'application/json'
  );
  loggedInHeaders = new HttpHeaders().set('Access-Control-Allow-Origin', '*')
    .set('Authorization', `Bearer ${localStorage.getItem('token')}`)
    .set('Content-Type', 'application/json');

    loggedInHeaders1 = new HttpHeaders().set('Access-Control-Allow-Origin', '*')
    .set('Authorization', `Bearer ${localStorage.getItem('token')}`)
    .set('Content-Type', 'application/json')
    .set('Content-Length', '0');


  firstPartOfUrl: string = 'http://localhost:';
  portUrl: string = '8080/';
  intializeTables = this.firstPartOfUrl + this.portUrl + 'auctionapp/';
  getCategories = this.firstPartOfUrl + this.portUrl + 'auctionapp/category/getAll';
  getSubcategories = this.firstPartOfUrl + this.portUrl + 'subcategory/getAll';
  getLastChanceProducts = this.firstPartOfUrl + this.portUrl + 'auctionapp/product/getLastChanceProducts';
  getNewProducts = this.firstPartOfUrl + this.portUrl + 'auctionapp/product/getNewProducts';
  getAllBid = this.firstPartOfUrl + this.portUrl + 'auctionapp/bid/getAll';
  addBid = this.firstPartOfUrl + this.portUrl + 'auctionapp/bid/addBid';
  getAllUsers = this.firstPartOfUrl + this.portUrl + 'auctionapp/user/getAll';
  getAllProduct = this.firstPartOfUrl + this.portUrl + 'auctionapp/product/getAll';
  login = this.firstPartOfUrl + this.portUrl + 'auctionapp/user/login';
  currentUser = this.firstPartOfUrl + this.portUrl + 'auctionapp/user/getCurrentUser'
  register = this.firstPartOfUrl + this.portUrl + 'auctionapp/user/register'
  update = this.firstPartOfUrl + this.portUrl + 'auctionapp/user/updateUser'
  delete = this.firstPartOfUrl + this.portUrl + 'auctionapp/user/deactivateUser'
<<<<<<< HEAD
<<<<<<< HEAD
  pay = this.firstPartOfUrl + this.portUrl + 'auctionapp/createPayment/'
=======
  pay = this.firstPartOfUrl + this.portUrl + '/auctionapp/createPayment/'
>>>>>>> e9a871bd (Add frontend part for payment)
=======
  pay = this.firstPartOfUrl + this.portUrl + 'auctionapp/createPayment/'
>>>>>>> 3c6c8490 (Allow the payment on the Stripe)


  constructor(private http: HttpClient) { }

  intializeDatabaseTables(): Observable<any> {
    return this.http.get(this.intializeTables, { 'headers': this.headers, responseType: 'text' })
  }

  getAllCategories(): Observable<{ categories: Category[] }> {
    return this.http.get<{ categories: Category[] }>(this.getCategories, { 'headers': this.headers, responseType: 'json' })
  }
  getLastChanceProduct(): Observable<{ products: Product[] }> {
    return this.http.get<{ products: Product[] }>(this.getLastChanceProducts, { 'headers': this.headers, responseType: 'json' })
  }

  getNewArrivalsProduct(): Observable<{ products: Product[] }> {
    return this.http.get<{ products: Product[] }>(this.getNewProducts, { 'headers': this.headers, responseType: 'json' })
  }

  getAllBids(): Observable<{ bids: Bid[] }> {
    return this.http.get<{ bids: Bid[] }>(this.getAllBid, { 'headers': this.loggedInHeaders });
  }

  addOneBid(bid: Bid): Observable<any> {
    return this.http.post<any>(this.addBid, JSON.stringify(bid), { 'headers': this.headersPost })
  }

  loginUser(loginRequest: LoginRequest): Observable<{ authResponse: AuthResponse }> {
    return this.http.post<{ authResponse: AuthResponse }>(this.login, JSON.stringify(loginRequest), { 'headers': this.headersPost })
  }

  getCurrentUser(): Observable<{ user: User }> {
    return this.http.get<{ user: User }>(this.currentUser, { 'headers': this.loggedInHeaders });
  }

  registerUser(registerRequest: RegisterRequest): Observable<{ user: User }> {
    return this.http.post<{ user: User }>(this.register, JSON.stringify(registerRequest), { 'headers': this.headersPost })
  }

  getAllProducts(): Observable<{ products: Product[] }> {
    return this.http.get<{ products: Product[] }>(this.getAllProduct, { 'headers': this.headers, responseType: 'json' })
  }

  updateUser(updateUserRequest:UpdateUser): Observable<{ user: User }> {
    return this.http.put<{ user: User }>(this.update, JSON.stringify(updateUserRequest),{ 'headers': this.loggedInHeaders, responseType: 'json' });
  }

  deleteUser(): Observable<{ reponse: Response }> {
    return this.http.delete<{  reponse: Response}>(this.delete,{ 'headers': this.loggedInHeaders, responseType: 'json' });
  }

<<<<<<< HEAD
=======

>>>>>>> 3c6c8490 (Allow the payment on the Stripe)
  payForProduct(paymentRequest:PaymentRequest): Observable<{ paymentResponse: PaymentResponse }> {
    return this.http.post<{ paymentResponse: PaymentResponse }>(this.pay, paymentRequest, { 'headers': this.loggedInHeaders})
  }
}
