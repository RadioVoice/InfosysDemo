import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { PaymentFormComponent } from './payment-form/payment-form.component';
import { SignupFormComponent } from './signup-form/signup-form.component';

import { Routes, RouterModule } from '@angular/router';
import { TopBarComponent } from './top-bar/top-bar.component'; // CLI imports router

const routes: Routes = [
  { path: 'payment-form', component: PaymentFormComponent },
  { path: 'signup-form', component: SignupFormComponent },
  { path: '**', component: PaymentFormComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    PaymentFormComponent,
    SignupFormComponent,
    TopBarComponent
  ],
  imports: [BrowserModule, HttpClientModule, FormsModule, ReactiveFormsModule, RouterModule.forRoot(routes)],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
