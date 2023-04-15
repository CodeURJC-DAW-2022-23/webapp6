import { Routes, RouterModule } from '@angular/router';

import { CommonComponent } from './components/common/common.component';
import { AdminPageComponent } from './components/admin-page/admin-page.component';

const appRoutes = [

    { path: '', component: CommonComponent },
    { path: 'common', component: CommonComponent },
    { path: 'admin', component: AdminPageComponent },

]

export const routing = RouterModule.forRoot(appRoutes);
