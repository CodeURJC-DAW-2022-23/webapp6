
import { RouterModule } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { AdminGuard } from './auth.guard';


import { IndexComponent } from './components/index/index.component';
import { AdminPageComponent } from './components/page-components/admin-page/admin-page.component';
import { UploadOfferComponent } from './components/page-components/upload-offer/upload-offer.component';
import { UpdateOfferComponent } from './components/page-components/update-offer/update-offer.component';
import { UploadReviewComponent } from './components/page-components/upload-review/upload-review.component';
import { UpdateReviewComponent } from './components/page-components/update-review/update-review.component';
import { CheckoutComponent } from './components/page-components/checkout/checkout.component';
import { BookGeneralComponent } from './components/page-components/book-general/book-general.component';
import { BookParticularComponent } from './components/page-components/book-particular/book-particular.component';
import { OfferComponent } from './components/page-components/offer/offer.component';
import { LoginComponent } from './components/page-components/login/login.components';
import { RegisterComponent } from './components/page-components/register/register.components';
import { UserComponent } from './components/page-components/user-page/user.components';
import { UpdateProfileComponent } from './components/page-components/update-profile/update-profile.component';
import { ContactComponent } from './components/page-components/contact-page/contact.component';
import { StatisticsComponent } from './components/page-components/statistics-page/statistics.component';

const appRoutes = [

    { path: '', component: IndexComponent },
    { path: 'index', component: IndexComponent },
    { path: 'admin', component: AdminPageComponent, canActivate: [AdminGuard] },
    { path: 'books', component: BookGeneralComponent },
    { path: 'books?searchtext=:searchWord', component: BookGeneralComponent },
    { path: 'books/:idBook', component: BookParticularComponent },
    { path: 'books/:idBook/upload-offer', component: UploadOfferComponent, canActivate: [AuthGuard] },
    { path: 'offers/:idOffer/update-offer', component: UpdateOfferComponent, canActivate: [AuthGuard] },
    { path: 'books/:idBook/upload-review', component: UploadReviewComponent, canActivate: [AuthGuard] },
    { path: 'reviews/:idReview/update-review', component: UpdateReviewComponent, canActivate: [AuthGuard] },
    { path: 'offers/:idOffer/checkout', component: CheckoutComponent, canActivate: [AuthGuard] },
    { path: 'offers/:idOffer', component: OfferComponent },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'user-page', component: UserComponent, canActivate: [AuthGuard] },
    { path: 'update-profile', component: UpdateProfileComponent, canActivate: [AuthGuard] },
    { path: 'update-review/:idReview', component: UpdateReviewComponent, canActivate: [AuthGuard] },
    { path: 'contact', component: ContactComponent },
    { path: 'statistics', component: StatisticsComponent },
    { path: '**', redirectTo: '' }

]

export const routing = RouterModule.forRoot(appRoutes);
