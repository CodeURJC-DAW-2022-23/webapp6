import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './components/app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { UpdateOfferComponent } from './components/update-offer/update-offer.component';
import { UpdateReviewComponent } from './components/update-review/update-review.component';
import { UploadOfferComponent } from './components/upload-offer/upload-offer.component';
import { UploadReviewComponent } from './components/upload-review/upload-review.component';
import {MatButtonModule} from '@angular/material/button';
import { CheckoutComponent } from './components/checkout/checkout.component';
import {MatCardModule} from '@angular/material/card';
import {MatDividerModule} from '@angular/material/divider';
import { CommonComponent } from './components/common/common.component';
import {MatIconModule} from '@angular/material/icon';
import { routing } from './app.routing';
import { AdminPageComponent } from './components/admin-page/admin-page.component';
import { BookParticularComponent } from './components/book-particular/book-particular.component';
import { OfferComponent } from './components/offer/offer.component';


//Import all components
//Import routing

@NgModule({
  declarations: [
    AppComponent, HeaderComponent, FooterComponent, UpdateOfferComponent, UpdateReviewComponent,
    UploadOfferComponent, UploadReviewComponent, CheckoutComponent, CommonComponent, AdminPageComponent,
    BookParticularComponent, OfferComponent
    //All components
  ],
  imports: [
    BrowserModule, FormsModule, HttpClientModule, BrowserAnimationsModule, MatInputModule, MatFormFieldModule,
    MatButtonModule, MatCardModule, MatDividerModule, MatIconModule, routing
    //Add routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
