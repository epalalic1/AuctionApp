import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Category } from '../models/category';
import { Product } from '../models/product';
import { User } from '../models/user';
import { Subcategory } from '../models/subcategory';
import { Bid } from '../models/bid';

@Injectable({
  providedIn: 'root'
})
export class InitializeService {
                                                                                                                                                                          
  headers = new HttpHeaders().set('Access-Control-Allow-Origin', '*');
  headersPost = new HttpHeaders().set('Access-Control-Allow-Origin', '*').set(
    'Content-Type', 'application/json'
  );
  
  intializeTables = 'http://localhost:8080/auctionapp/';
  getCategories = 'http://localhost:8080/auctionapp/category/getAll';
  getSubcategories = 'http://localhost:8080/auctionapp/subcategory/getAll';
  getLastChanceProducts = 'http://localhost:8080/auctionapp/product/getLastChanceProducts';
  getNewProducts = 'http://localhost:8080/auctionapp/product/getNewProducts';
  getAllBid = 'http://localhost:8080/auctionapp/bid/getAll';
  addBid = 'http://localhost:8080/auctionapp/bid/addBid';
  getAllUsers = 'http://localhost:8080/auctionapp/user/getAll';
  listOfSubcategories!: Subcategory[];

  constructor(private http: HttpClient) {


  }
  intializeDatabaseTables() :Observable<any>{
    return this.http.get(this.intializeTables,{'headers':this.headers, responseType: 'text'})
  }
  /*intializeDatabaseTables(): void {
   this.http.get(this.intializeTables,{'headers':this.headers, responseType: 'text'}).subscribe(Response => {


    });
  }*/
 

  getAllSubcategories() :void {
    let list1 : Subcategory [] = [];
    this.http.get(this.getSubcategories,{'headers':this.headers,responseType:'json'}).subscribe(Response => {
      let a = JSON.parse(JSON.stringify(Response));
      for (let subcategory1 of Object.values(a)) {
        let sub = JSON.parse(JSON.stringify(subcategory1));
        let sub1 = new Subcategory(sub.id,sub.name,sub.category.id);
        list1.push(sub1);
      }
  })
    this.listOfSubcategories = list1;
  }

  getSubcategoriesForCategory(id:number) :string []{
    let list1 : string [] = [];
    for (let subcategory of this.listOfSubcategories) {
      if (subcategory.category == id) {
        list1.push(subcategory.name);
      }
    }
    return list1;
  }

  getAllCategories(): Category[] {
    let list: Category [] =[] ;
    this.getAllSubcategories();
    this.http.get(this.getCategories,{'headers':this.headers,responseType:'json'}).subscribe(Response => {
        let a = JSON.parse(JSON.stringify(Response));
        for(let b of Object.values(a)){
          let c = JSON.parse(JSON.stringify(b));
          let help = new Category(c.id,c.name,this.getSubcategoriesForCategory(c.id),false);
          list.push(help);
        }
  
    })
    return list;
  }

  getLastChanceProduct() :Product[] {
    let list: Product [] =[] ;
    this.http.get(this.getLastChanceProducts,{'headers':this.headers,responseType:'json'}).subscribe(Response => {
      let a = JSON.parse(JSON.stringify(Response));
      for(let b of Object.values(a)){
        let c = JSON.parse(JSON.stringify(b));
        let help = new Product(c.id,c.name,c.dateOfArriving,c.endDate,c.startPrice,c.details,
          c.status,c.price,1,c.userId,c.imageName);
        list.push(help);
      }

  })

    return list
  }

  getNewArrivalsProduct() :Product[] {
    let list: Product [] =[] ;
    this.http.get(this.getNewProducts,{'headers':this.headers,responseType:'json'}).subscribe(Response => {
      let a = JSON.parse(JSON.stringify(Response));
      for(let b of Object.values(a)){
        let c = JSON.parse(JSON.stringify(b)); 
        let help = new Product(c.id,c.name,c.dateOfArriving,c.endDate,c.startPrice,c.details,
          c.status,c.price,1,c.userId,c.imageName);
        list.push(help);
      }

  })
    return list
  }
  
  getAllBids() :Observable<{bids:Bid[]}> {
    return this.http.get<{bids:Bid[]}>(this.getAllBid,{'headers':this.headers});
  }

  addOneBid(bid:Bid):Observable<any>{
    return this.http.post<any>(this.addBid,JSON.stringify(bid),{'headers':this.headersPost})
  }

  getUsers() :Observable<{users:User[]}> {
    return this.http.get<{users:User[]}>(this.getAllUsers,{'headers':this.headers})
  }
}
