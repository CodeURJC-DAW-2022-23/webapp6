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


//Import all components
//Import routing

@NgModule({
  declarations: [
    AppComponent, HeaderComponent, FooterComponent, UpdateOfferComponent, UpdateReviewComponent,
    UploadOfferComponent, UploadReviewComponent
    //All components
  ],
  imports: [
    BrowserModule, FormsModule, HttpClientModule, BrowserAnimationsModule, MatInputModule, MatFormFieldModule
    //Add routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
