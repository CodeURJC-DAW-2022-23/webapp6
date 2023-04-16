import { Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CommonComponent } from './components/common/common.component';
import { AdminPageComponent } from './components/admin-page/admin-page.component';
import { UploadOfferComponent } from './components/upload-offer/upload-offer.component';
import { UpdateOfferComponent } from './components/update-offer/update-offer.component';
import { UploadReviewComponent } from './components/upload-review/upload-review.component';
import { UpdateReviewComponent } from './components/update-review/update-review.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { BookParticularComponent } from './components/book-particular/book-particular.component';

const appRoutes = [

    { path: '', component: CommonComponent },
    { path: 'common', component: CommonComponent },
    { path: 'admin', component: AdminPageComponent },
    { path: 'books/:id', component: BookParticularComponent},
    { path: 'books/:id/upload-offer', component: UploadOfferComponent},
    { path: 'offers/:id/update-offer', component: UpdateOfferComponent},
    { path: 'books/:id/upload-review', component: UploadReviewComponent},
    { path: 'reviews/:id/update-review', component: UpdateReviewComponent},
    { path: 'offers/:id/checkout', component: CheckoutComponent},

]

export const routing = RouterModule.forRoot(appRoutes);
