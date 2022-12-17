import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductOverviewComponent } from './core/components/productOverview/productoverview.component';
import { ShopComponent } from './core/components/shop/shop.component';

const routes: Routes = [
  { path: 'Product', component: ProductOverviewComponent },
  { path: 'Category', component: ShopComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
