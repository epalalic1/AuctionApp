import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { LoginFormComponent } from './core/components/login-form/login-form.component';
import { ProductOverviewComponent } from './core/components/productOverview/productoverview.component';
import { ShopComponent } from './core/components/shop/shop.component';
import { RegisterFormComponent } from './core/components/register-form/register-form.component';

const routes: Routes = [
  { path: 'Product', component: ProductOverviewComponent },
  { path: 'Login', component: LoginFormComponent },
  { path: 'Register', component: RegisterFormComponent },
  { path: 'Home', component: AppComponent },
  { path: 'Category', component: ShopComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
