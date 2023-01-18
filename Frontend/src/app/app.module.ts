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
    LoginFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSidenavModule,
    MatTabsModule,
    MatMenuModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
