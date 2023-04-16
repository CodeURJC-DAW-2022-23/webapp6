import { Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CommonComponent } from './components/common/common.component';
import { AdminPageComponent } from './components/page-components/admin-page/admin-page.component';
import { UploadOfferComponent } from './components/page-components/upload-offer/upload-offer.component';
import { UpdateOfferComponent } from './components/page-components/update-offer/update-offer.component';
import { UploadReviewComponent } from './components/page-components/upload-review/upload-review.component';
import { UpdateReviewComponent } from './components/page-components/update-review/update-review.component';
import { CheckoutComponent } from './components/page-components/checkout/checkout.component';
import { BookParticularComponent } from './components/page-components/book-particular/book-particular.component';
import { OfferComponent } from './components/page-components/offer/offer.component';

const appRoutes = [

    { path: '', component: CommonComponent },
    { path: 'common', component: CommonComponent },
    { path: 'admin', component: AdminPageComponent },
    { path: 'books/:idBook', component: BookParticularComponent},
    { path: 'books/:idBook/upload-offer', component: UploadOfferComponent},
    { path: 'offers/:idOffer/update-offer', component: UpdateOfferComponent},
    { path: 'books/:idBook/upload-review', component: UploadReviewComponent},
    { path: 'reviews/:idReview/update-review', component: UpdateReviewComponent},
    { path: 'books/:idBook/offers/:idOffer/checkout', component: CheckoutComponent},
    { path: 'books/:idBook/offers/:idOffer', component: OfferComponent},
    { path: '**', redirectTo: '' }

]

export const routing = RouterModule.forRoot(appRoutes);
