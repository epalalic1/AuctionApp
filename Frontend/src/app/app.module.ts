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
import { MatMenuModule} from '@angular/material/menu';
import { MatTabsModule } from '@angular/material/tabs';
import { HttpClientModule } from '@angular/common/http';
import { MatSidenavModule } from '@angular/material/sidenav';
import { ProductoverviewComponent } from './core/components/productoverview/productoverview.component';

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
    ProductoverviewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSidenavModule,
    MatTabsModule,
    MatMenuModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
