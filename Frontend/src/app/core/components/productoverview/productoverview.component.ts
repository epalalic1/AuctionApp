import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { AuthGuard } from '../../guards/auth.guard';
import { Bid } from '../../models/bid';
import { Product } from '../../models/product';
import { ApiService } from '../../services/api.service';
import { BidService } from '../../services/bid.service';

@Component({
  selector: 'app-productOverview',
  templateUrl: './productOverview.component.html',
  styleUrls: ['./productOverview.component.css']
})
export class ProductOverviewComponent implements OnInit {

  @Input()
  product!: Product;

  highestBid: number = 0;

  bids: number = 0;

  inputValue = "";

  @Input()
  list1!: Bid[];

  clicked: Number = 0;

  check: Number = 0;

  timeLeft: any = 0;

  higherBid:number = 0;

  lowerBid:number = 0;

  hide: number = 0;

  hideText = 0;

  userRole: number = 0;

  areSame: number = 0;

  images: string[] = [];

  authG!: AuthGuard;


  constructor(private route: ActivatedRoute,
    private bidService: BidService,
    private router: Router,
    private apiService: ApiService,
    private authGuard:AuthGuard) {
      this.authG = authGuard
     }

  ngOnInit(): void {
    this.areSame = 0;
    let user = this.bidService.getUsersRole();
    this.userRole = user.roleId;
    this.route.queryParams.subscribe((params: any) => {
      this.product = new Product(
        params.id,
        params.name,
        params.dateOfArriving,
        params.endDate,
        params.startPrice,
        params.details,
        params.status,
        params.price,
        params.subcategoryId,
        params.userId,
        params.imageName,
        params.categoryId
      );
      this.images = this.product.imageName;
      this.areSame = 0
      if (this.product.userId == this.userRole) {
        this.areSame = 1;
      }
      this.highestBid = this.bidService.getHighestBidForProduct(this.product.id);
      this.bids = this.bidService.getNumberOfBidsForProduct(this.product.id);
      let date: any = new Date();
      let parsed = Date.parse(this.product.endDate.toString());
      let diffInMs = Math.abs(parsed - date);
      this.timeLeft = Number((diffInMs / (1000 * 60 * 60 * 24))).toFixed(0);
      this.clicked = 0;
      this.hide = 0;
      this.hideText = 0;
    })
  }

  onKey(event: any) {
    this.inputValue = event.target.value;
  }

  onClick(): void {
    this.clicked = 1;
    let valueOfInput = Number(this.inputValue);
    this.hideText = 1;
    if (valueOfInput > Number(this.highestBid)) {
      this.hide = 1;
      this.higherBid = 1;
      this.lowerBid = 0;
      let bid = new Bid(
        this.bidService.listOfBids.length - 1,
        valueOfInput,
        new Date(),
        this.product.id, this.bidService.getUsersRole().id
      );
      this.apiService.addOneBid(bid).subscribe((response) => {
        this.highestBid = valueOfInput;
        this.bids = this.bids + 1;
      });
      this.router.events.subscribe((evt: any) => {
        if (!(evt instanceof NavigationEnd)) {
          return;
        }
        window.scrollTo(0, 0)
      });
    }
    else {
      this.higherBid = 0
      this.lowerBid = 1;
    }
  }
}
