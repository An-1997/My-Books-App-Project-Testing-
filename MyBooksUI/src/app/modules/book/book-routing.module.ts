import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuardService } from '../../auth-guard.service';
import { FavouriteComponent } from './components/favourite/favourite.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

const bookRoutes: Routes = [{
    path : 'books',
    children : [       
        {
            path : 'home',
            component : DashboardComponent,
            canActivate:[AuthGuardService]            
        },
        {
            path : 'favourite',
            component : FavouriteComponent,
            canActivate:[AuthGuardService]            
        }        
    ]

}];

@NgModule({
    imports:[RouterModule.forChild(bookRoutes)],
    exports :[RouterModule ]
})
export class BookRoutingModule{}