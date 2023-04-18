import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './components/app.component';
import { HeaderComponent } from './components/general-components/header/header.component';
import { FooterComponent } from './components/general-components/footer/footer.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { UpdateOfferComponent } from './components/page-components/update-offer/update-offer.component';
import { UpdateReviewComponent } from './components/page-components/update-review/update-review.component';
import { UploadOfferComponent } from './components/page-components/upload-offer/upload-offer.component';
import { UploadReviewComponent } from './components/page-components/upload-review/upload-review.component';
import { MatButtonModule } from '@angular/material/button';
import { CheckoutComponent } from './components/page-components/checkout/checkout.component';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { CommonComponent } from './components/common/common.component';
import { MatIconModule } from '@angular/material/icon';
import { routing } from './app.routing';
import { AdminPageComponent } from './components/page-components/admin-page/admin-page.component';
import { BookParticularComponent } from './components/page-components/book-particular/book-particular.component';
import { OfferComponent } from './components/page-components/offer/offer.component';

import { SectionTitle, GeneralTitle } from './components/general-components/titles/titles.component';
import { Form } from './components/general-components/forms/forms.component';
import { OfferCard } from './components/general-components/offer-card/offer-card.component';
import { ReviewCard } from './components/general-components/review-card/review-card.component';
import { LoginComponent } from './components/page-components/login/login.components';
import { LoginFormComponent } from './components/page-components/login-form/login-form.component';




//Import all components
//Import routing

@NgModule({
  declarations: [
    AppComponent, HeaderComponent, FooterComponent, UpdateOfferComponent, UpdateReviewComponent,
    UploadOfferComponent, UploadReviewComponent, CheckoutComponent, CommonComponent, AdminPageComponent,
    BookParticularComponent, OfferComponent, SectionTitle, GeneralTitle, Form, OfferCard, LoginComponent,
    ReviewCard, LoginFormComponent
    //All components
  ],
  imports: [
    BrowserModule, FormsModule, HttpClientModule, BrowserAnimationsModule, MatInputModule, MatFormFieldModule,
    MatButtonModule, MatCardModule, MatDividerModule, MatIconModule, routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
