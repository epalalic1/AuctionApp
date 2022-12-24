import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { environment } from 'src/environments/environments';
import { AuthGuard } from '../../guards/auth.guard';
import { Bid } from '../../models/bid';
import { Product } from '../../models/product';
import { User } from '../../models/user';
import { ApiService } from '../../services/api.service';
import { BidService } from '../../services/bid.service';
import { ProductUtils } from '../../utils/product-utils';

@Component({
  selector: 'app-product-overview',
  templateUrl: './product-overview.component.html',
  styleUrls: ['./product-overview.component.css']
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

  higherBid: number = 0;

  lowerBid: number = 0;

  hide: number = 0;

  hideText = 0;

  userRole: number = 0;

  areSame: number = 0;

  images: string[] = [];

  authG!: AuthGuard;

  user: User = new User();

  paymentHandler: any = null;

  displayPaymentButton = false;

  proba = false;

  constructor(private route: ActivatedRoute,
    private bidService: BidService,
    private router: Router,
    private apiService: ApiService,
    private authGuard: AuthGuard) {
    this.authG = authGuard
  }

  ngOnInit(): void {
    this.invokeStripe();
    this.displayPaymentButton = false;
    this.areSame = 0;
    let user = this.bidService.getUsersRole();
    if (localStorage.getItem('token') != null) {
      this.apiService.getCurrentUser().subscribe((user) => {
        this.user = <User>JSON.parse(JSON.stringify(user));
      })
    }
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
      if (this.product.userId == this.user.id) {
        this.areSame = 1;
      }
      this.highestBid = this.bidService.getHighestBidForProduct(this.product.id);
      this.bids = this.bidService.getNumberOfBidsForProduct(this.product.id);
      this.timeLeft = ProductUtils.findTimeLeftForProduct(this.product)
      this.clicked = 0;
      this.hide = 0;
      this.hideText = 0;
      let result = ProductUtils.findTimeLeftForProduct(this.product).split(" ")[0];
      if (Number(result) <= 0) {
        console.log("Usli smo ovdje"); 
        if (localStorage.getItem('token') != null) {
          console.log("Usli smo ovdje isto");
          this.apiService.getCurrentUser().subscribe((curruser) => {
            this.apiService.getAllBids().subscribe((bids) => {
              let currentUser = <User>JSON.parse(JSON.stringify(curruser));
              let allBids = <Bid[]>JSON.parse(JSON.stringify(bids));
              this.checkIfCurrentUserIsHighestBidder(currentUser, allBids)? this.displayPaymentButton = true:this.displayPaymentButton = false;
            })
          })
        }
      }
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


  makePayment(amount: any) {
    const paymentHandler = (<any>window).StripeCheckout.configure({
      key: environment.stripe.api_key,
      locale: 'auto',
      token: function (stripeToken: any) {
        console.log(stripeToken);
        alert('Stripe token generated!');
        this.apiService.payForProduct().subscribe((result:any) => {
          JSON.parse(JSON.stringify(result));
        })
      },
    });
    paymentHandler.open({
      name: 'Positronx',
      description: '3 widgets',
      amount: amount * 100,
    });
  }

  invokeStripe() {
    if (!window.document.getElementById('stripe-script')) {
      const script = window.document.createElement('script');
      script.id = 'stripe-script';
      script.type = 'text/javascript';
      script.src = 'https://checkout.stripe.com/checkout.js';
      script.onload = () => {
        this.paymentHandler = (<any>window).StripeCheckout.configure({
          key: environment.stripe.api_key,
          locale: 'auto',
          token: function (stripeToken: any) {
            console.log(stripeToken);
            alert('Payment has been successfull!');
          },
        });
      };
      window.document.body.appendChild(script);
    }
  }

  checkIfCurrentUserIsHighestBidder(user: User, bids: Bid[]) {
    let productBids = bids.filter(bid => bid.productId == this.product.id);
    if (productBids.length != 0) {
      let amount = ProductUtils.findHighestBid(productBids);
      let bid = productBids.find(item => item.userId == user.id && item.amount == amount && item.productId == this.product.id);
      if (bid != undefined) {
        return true;
      }
      return false;
    }
    return false;
  }
}
