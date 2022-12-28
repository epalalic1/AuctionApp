import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { environment } from 'src/environments/environments';
import { AuthGuard } from '../../guards/auth.guard';
import { Bid } from '../../models/bid';
import { BidderForProduct } from '../../models/bidder-for-product';
import { PaymentRequest } from '../../models/payment-request';
import { Product } from '../../models/product';
import { User } from '../../models/user';
import { ApiService } from '../../services/api.service';
import { BidService } from '../../services/bid.service';
import { ProductUtils } from '../../utils/product-utils';
import { getStorage, ref, getDownloadURL } from "firebase/storage";
import { ItemComponent } from '../item/item.component';


@Component({
  selector: 'app-productOverview',
  templateUrl: './productOverview.component.html',
  styleUrls: ['./productOverview.component.css']
})
export class ProductOverviewComponent implements OnInit {

  product: Product = new Product;

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

  sold: string = "false";

  relatedProducts: Product[] = [];

  listOfBidders: BidderForProduct[] = [];

  imagesOfProduct: string[] = [];


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
      this.userRole = this.user.roleId;
      let id = Number(this.route.snapshot.paramMap.get('id'));
      this.apiService.getProductById(id).subscribe((productRes) => {
        this.product = <Product>JSON.parse(JSON.stringify(productRes));
        const storage = getStorage();
        for (const img of this.product.imageName) {
          getDownloadURL(ref(storage, img))
            .then((url) => {
              this.imagesOfProduct.push(url);
            })
        }
        this.product.imageName = this.imagesOfProduct;
        this.images = this.product.imageName;
        this.highestBid = this.bidService.getHighestBidForProduct(this.product.id);
        this.bids = this.bidService.getNumberOfBidsForProduct(this.product.id);
        this.timeLeft = ProductUtils.findTimeLeftForProduct(this.product)
        this.clicked = 0;
        this.hide = 0;
        this.hideText = 0;
        let result = ProductUtils.findTimeLeftForProduct(this.product).split(" ")[0];
        this.sold = this.product.status.toString();
        this.sold = this.sold.toString()
        if (localStorage.getItem('token') != null) {
          this.apiService.getCurrentUser().subscribe((user) => {
            this.user = <User>JSON.parse(JSON.stringify(user));
            if (this.product.userId == this.user.id) {
              this.areSame = 1;
              this.apiService.getBiddersForProduct(this.product.id).subscribe((bidders) => {
                this.listOfBidders = JSON.parse(JSON.stringify(bidders));
                this.relatedProducts.splice(5, this.listOfBidders.length);
              })
            }
            else {
              this.areSame = 0;
              this.apiService.getAllProducts().subscribe((products) => {
                let allProducts = JSON.parse(JSON.stringify(products));
                this.relatedProducts = allProducts.filter((item: Product) =>
                  item.categoryId == this.product.categoryId
                  && item.userId != this.user.id
                );
                this.relatedProducts.splice(3, this.relatedProducts.length);
              })
            }
          })
        }
      })
          this.apiService.getCurrentUser().subscribe((curruser) => {
            this.apiService.getAllBids().subscribe((bids) => {
              let currentUser = <User>JSON.parse(JSON.stringify(curruser));
              let allBids = <Bid[]>JSON.parse(JSON.stringify(bids));
              this.checkIfCurrentUserIsHighestBidder(currentUser, allBids) ? this.displayPaymentButton = true : this.displayPaymentButton = false;
            })
          })
        }
      })
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
      console.log("Usli smo ovdje");
      this.hide = 1;
      this.higherBid = 1;
      this.lowerBid = 0;
      let bid = new Bid(
        this.bidService.listOfBids.length - 1,
        valueOfInput,
        new Date(),
        this.product.id,
        this.bidService.getUsersRole().id
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

  /**
   * The method we use it to create payment in Stripe)
   * @param amount we are paying for the product
   * @returns in case that the user has already paid for this product
   */

  makePayment(amount: any) {
    if (this.product.status.toString() === "true") {
      window.alert("You have already paid this product");
      return;
    }
    const paymentHandler = (<any>window).StripeCheckout.configure({
      key: environment.stripe.api_key,
      locale: 'auto',
      token: function (stripeToken: any) {
        console.log(stripeToken);
        alert('Stripe token generated!');
        payment(stripeToken.id);
      },
    });

    const payment = (token: string) => {
      let paymentRequest = new PaymentRequest(
        "usd",
        "3 widgets",
        amount,
        this.user.email,
        token,
        this.product.id
      );
      this.apiService.payForProduct(paymentRequest).subscribe((paymentR) => {
        window.alert(JSON.parse(JSON.stringify(paymentR)));
        window.location.href = '/';
      })
    }
    paymentHandler.open({
      name: 'AuctionApp',
      description: 'AuctionAppPaymeny',
      amount: amount * 100,
    });
  }

  /**
   * The method we use it to invoke Stripe when page is loaded for the first time
   */

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

