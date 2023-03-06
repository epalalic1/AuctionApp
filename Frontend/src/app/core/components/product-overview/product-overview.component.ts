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
import { AppComponent } from 'src/app/app.component';
import { MatDialog } from '@angular/material/dialog';
import { PopUpComponent } from 'src/app/shared/components/pop-up/pop-up.component';
import { WebSocketAPI } from '../../models/web-socket-api';
import { WsNotification } from '../../models/ws-notification';

@Component({
  selector: 'app-product-overview',
  templateUrl: './product-overview.component.html',
  styleUrls: ['./product-overview.component.css']
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

  allProducts: Product[] = [];

  productPayed!: boolean;

  webSocketAPI!: WebSocketAPI;


  constructor(private route: ActivatedRoute,
    private bidService: BidService,
    private router: Router,
    private apiService: ApiService,
    private authGuard: AuthGuard,
    private appComponent: AppComponent,
    private matDialog: MatDialog) {
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
            this.currentUserValidation(this.user, result);
            if (this.product.userId == this.user.id) {
              this.areSame = 1;
              this.apiService.getBiddersForProduct(this.product.id).subscribe((bidders) => {
                this.listOfBidders = JSON.parse(JSON.stringify(bidders));
                this.listOfBidders?.length ? this.listOfBidders.splice(5, this.listOfBidders.length) : null;
              })
            }
            else {
              this.areSame = 0;
              this.apiService.getAllProducts().subscribe((products) => {
                let allProducts = <Product[]>JSON.parse(JSON.stringify(products));
                this.relatedProducts = allProducts.filter((item: Product) =>
                  item.subcategoryId == this.product.subcategoryId
                  && item.userId != this.user.id
                );
                this.relatedProducts?.length ? ProductUtils.productsWithListOfImages(this.relatedProducts, this.appComponent.listOfProductsImages) : null;
                this.relatedProducts?.length ? this.relatedProducts.splice(3, this.relatedProducts.length) : null;
              })
            }
          })
        }
      })
    })
  }

  /**
   * method that checks whether the current user is the one who placed the
   * product or is the highest bidder for this product
   * @param user is currently logged in user
   * @param result is string that represents how many day is left for bidding for this product
   */

  currentUserValidation(user: User, result: string) {
    this.userRole = this.user.roleId;
    if (this.product.userId == this.user.id) {
      this.areSame = 1;
    }
    if (Number(result) <= 0) {
      this.apiService.getAllBids().subscribe((bids) => {
        let allBids = <Bid[]>JSON.parse(JSON.stringify(bids));
        this.checkIfCurrentUserIsHighestBidder(this.user, allBids) ? this.displayPaymentButton = true : this.displayPaymentButton = false;
        this.product.status.toString() === "true" ? this.productPayed = true : this.productPayed = false;
      })
    }
  }

  onKey(event: any) {
    this.inputValue = event.target.value;
  }

  onClick(): void {
    this.clicked = 1;
    let valueOfInput = Number(this.inputValue);
    this.hideText = 1;
    if (valueOfInput > Number(this.highestBid)) {
      this.webSocketAPI = new WebSocketAPI(this.appComponent, this.apiService);
      console.log(this.webSocketAPI);
      let wsNotification = new WsNotification(
        "Someone just placed a higher bid than you did on the product",
        this.user.id,
        this.product.id,
        false
      )
      this.appComponent.onClick(wsNotification);
      this.hide = 1;
      this.higherBid = 1;
      this.lowerBid = 0;
      let bid = new Bid(
        0,
        valueOfInput,
        new Date(),
        this.product.id,
        this.user.id
      );
      this.apiService.addOneBid(bid).subscribe((response) => {
        setTimeout(
          () => {
            window.location.reload();
          }, 2000);
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
   * The method we use it to create payment in Stripe
   * @param amount we are paying for the product
   * @returns in case that the user has already paid for this product
   */

  makePayment(amount: any) {

    const paymentHandler = (<any>window).StripeCheckout.configure({
      key: environment.stripe.api_key,
      locale: 'auto',
      token: function (stripeToken: any) {
        payment(stripeToken.id);
      },
    });
    const payment = (token: string) => {
      this.matDialog.open(PopUpComponent, {
        data: {
          disabled: false
        }
      });
      setTimeout(() => {
        let paymentRequest = new PaymentRequest(
          "usd",
          "AuctionApp",
          amount,
          this.user.email,
          token,
          this.product.id
        );
        this.apiService.payForProduct(paymentRequest).subscribe((paymentR) => {
          this.matDialog.closeAll();
          this.matDialog.open(PopUpComponent, {
            data: {
              disabled: true
            }
          });
          setTimeout(() => {
            window.location.reload();
          }, 2500);
        })
      }, 2000);
    }
    paymentHandler.open({
      name: 'AuctionApp',
      description: 'AuctionAppPayment',
      amount: amount * 100,
    })
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
            alert('Payment has been successfull!');
          },
        });
      };
      window.document.body.appendChild(script);
    }
  }

  checkIfCurrentUserIsHighestBidder(user: User, bids: Bid[]) {
    if (bids?.length) {
      let productBids = bids.filter(bid => bid.productId == this.product.id);
      if (productBids.length != 0) {
        let amount = ProductUtils.findHighestBid(productBids);
        let bid = productBids.find(item => item.userId == user.id && item.amount == amount && item.productId == this.product.id);
        if (bid != undefined) {
          return true;
        }
        return false;
      }
    }
    return false;
  }
}
