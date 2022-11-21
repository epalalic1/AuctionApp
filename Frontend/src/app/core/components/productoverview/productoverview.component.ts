import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { Bid } from '../../models/bid';
import { Product } from '../../models/product';
import { BidServiceService } from '../../services/bid-service.service';
import { InitializeService } from '../../services/initialize.service';

@Component({
  selector: 'app-productoverview',
  templateUrl: './productoverview.component.html',
  styleUrls: ['./productoverview.component.css']
})
export class ProductoverviewComponent implements OnInit {

  productId!: any;
  @Input()
  product: any;
  highestBid = 0;
  bids = 0;
  inputValue = "";
  @Input()
  list1! : Bid[];
  clicked: Number = 0;
  check:Number = 0;
  timeLeft:any = 0;
  value: number = 1;
  hide:number = 0;
  hideText = 0;
  userRole:number = 0;
  areSame:number  = 0;

  constructor(private route:ActivatedRoute,
              private bidServis:BidServiceService,
              private router:Router,
              private servis:InitializeService) { }

  ngOnInit(): void {
    this.areSame = 0;
    let user = this.bidServis.getUsersRole();
    this.userRole = user.roleId;
    this.route.queryParams.subscribe((params:any) =>{
      this.product = new Product(params.id,params.name,params.dateOfArriving,params.endDate,
        params.startPrice,params.details,params.status,params.price,1,params.userId,params.imageName);
        if (this.product.userId == user.id) {
          this.areSame = 1;
        }
        else {
          this.areSame = 0
        }
        this.highestBid = this.bidServis.getHighestBidForProduct(this.product.id);
        this.bids = this.bidServis.getNumberOfBidsForProduct(this.product.id); 
        let date:any  = new Date();
        let parsed =  Date.parse(this.product.endDate);
        let diffInMs = Math.abs(parsed - date);
        this.timeLeft = Number((diffInMs / (1000 * 60 * 60 * 24))).toFixed(0);
        this.clicked = 0;
        this.hide = 0;
        this.hideText = 0;
    })
    
  
  }
  onKey(event :any) {this.inputValue= event.target.value;}
  onClick():void{
    this.clicked = 1;
    let valueOfInput = Number(this.inputValue);
    this.hideText = 1;
    if (valueOfInput > Number(this.highestBid)) {
      this.hide = 1;
      this.value = 1;
      let a  = new Bid(this.bidServis.listOfBids.length-1,valueOfInput,new Date(),this.product.id,1);
      this.servis.addOneBid(a).subscribe((response)=>{
      });
      this.router.events.subscribe((evt: any) => {
        if (!(evt instanceof NavigationEnd)) {
            return;
        }
        window.scrollTo(0, 0)
    });
    }
    else {
     this.value = 0;
    }
  }
}
