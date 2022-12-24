import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TabComponent } from './core/components/tab/tab.component';
import { MiddleComponent } from './core/components/middle/middle.component';
import { DetailsComponent } from './core/components/details/details.component';
import { ItemComponent } from './core/components/item/item.component';
import { LastChanceComponent } from './core/components/last-chance/last-chance.component';
import { NewArrivalsComponent } from './core/components/new-arrivals/new-arrivals.component';
import { SidemenuComponent } from './core/components/sidemenu/sidemenu.component';
import { NavbarComponent } from './core/components/navbar/navbar.component';
import { FooterComponent } from './core/components/footer/footer.component';
import { SubcategoryComponent } from './core/components/subcategory/subcategory.component';
import { MatMenuModule } from '@angular/material/menu';
import { MatTabsModule } from '@angular/material/tabs';
import { HttpClientModule } from '@angular/common/http';
import { MatSidenavModule } from '@angular/material/sidenav';
import { ProductOverviewComponent } from './core/components/product-overview/product-overview.component';
import { ImagesComponent } from './core/components/images/images.component';
import { NotificationComponent } from './core/components/notification/notification.component';
import { ShopComponent } from './core/components/shop/shop.component';
import { RecommendedProductsComponent } from './core/components/recommended-products/recommended-products.component';
import { RecommendedTabComponent } from './core/components/recommended-tab/recommended-tab.component';
import { RegisterFormComponent } from './core/components/register-form/register-form.component';
import { LoginFormComponent } from './core/components/login-form/login-form.component';
import { FormsModule } from '@angular/forms';
import { MyAccountComponent } from './core/components/my-account/my-account.component';
import { SettingsComponent } from './core/components/settings/settings.component';
import { SellerTabComponent } from './core/components/seller-tab/seller-tab.component';
import { BidTabComponent } from './core/components/bid-tab/bid-tab.component';
import { ProfileComponent } from './core/components/profile/profile.component';
import { MatButtonModule } from '@angular/material/button';
import { TableItemComponent } from './core/components/table-item/table-item.component';
import { SocialNetworkLogComponent } from './shared/components/social-network-log/social-network-log.component';
import { SocialNetworkBtnsComponent } from './shared/components/social-network-btns/social-network-btns.component';
import { DatePickerBirthdayComponent } from './shared/components/date-picker-birthday/date-picker-birthday.component';
import { AddressCardInformationComponent } from './shared/components/address-card-information/address-card-information.component';
import { AddItemComponent } from './core/components/add-item/add-item.component';
import { ShippingComponent } from './core/components/shipping/shipping.component';
import { BasicComponent } from './core/components/basic/basic.component';
import { BidderItemComponent } from './core/components/bidder-item/bidder-item.component';
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from '@angular/material/form-field'
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { NgxPaginationModule } from 'ngx-pagination';

@NgModule({
  declarations: [
    AppComponent,
    TabComponent,
    MiddleComponent,
    DetailsComponent,
    ItemComponent,
    LastChanceComponent,
    NewArrivalsComponent,
    SidemenuComponent,
    NavbarComponent,
    FooterComponent,
    SubcategoryComponent,
    ProductOverviewComponent,
    ImagesComponent,
    NotificationComponent,
    ShopComponent,
    RecommendedProductsComponent,
    RecommendedTabComponent,
    RegisterFormComponent,
    LoginFormComponent,
    MyAccountComponent,
    SettingsComponent,
    SellerTabComponent,
    BidTabComponent,
    ProfileComponent,
    TableItemComponent,
    SocialNetworkLogComponent,
    SocialNetworkBtnsComponent,
    DatePickerBirthdayComponent,
    AddressCardInformationComponent,
    AddItemComponent,
    AddItemComponent,
    ShippingComponent,
    BasicComponent,
    AddItemComponent,
    BidderItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSidenavModule,
    MatTabsModule,
    MatMenuModule,
    HttpClientModule,
    FormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatSelectModule,
    MatAutocompleteModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
