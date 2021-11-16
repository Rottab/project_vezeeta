import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {HomeComponent} from "./home/home.component";
import {AppointmentListComponent} from "./appointment-list/appointment-list.component";
import {CategoryListComponent} from "./category-list/category-list.component";
import {DoctorListComponent} from "./doctor-list/doctor-list.component";
import {AuthComponent} from "./auth/auth.component";
import {DoctorComponent} from "./doctor-list/doctor/doctor.component";
import {UserComponent} from "./user/user.component";
import {SigninComponent} from "./auth/signin/signin.component";
import {SignupComponent} from "./auth/signup/signup.component";
import {AppointmentComponent} from "./appointment-list/appointment/appointment.component";
import {AppointmentBookComponent} from "./appointment-list/appointment-book/appointment-book.component";
import {AuthGuard} from "./auth/auth.guard";
import {DoctorGuard} from "./auth/doctor.guard";
import {PartnerComponent} from "./user/partner/partner.component";
import {RegularComponent} from "./user/regular/regular.component";
import {ErrorComponent} from "./error/error.component";


const routes: Routes = [
  {path: '', component: HomeComponent},
  // should place these appointments under appointment route
  {path: 'appointments', component: AppointmentListComponent, canActivate: [AuthGuard]},
  {path: 'appointments/book', component: AppointmentBookComponent, canActivate: [AuthGuard]},
  {path: 'appointments/:id', component: AppointmentComponent, canActivate: [AuthGuard]},
  {path: 'categories', component: CategoryListComponent, canActivate: [AuthGuard]},
  {path: 'doctors', component: DoctorListComponent, canActivate: [AuthGuard]},
  {path: 'doctors/:id', component: DoctorComponent, canActivate: [AuthGuard]},
  {
    path: 'auth', component: AuthComponent, children: [
      {path: 'signin', component: SigninComponent},
      {path: 'signup', component: SignupComponent},
    ]
  },
  {
    path: 'user', component: UserComponent, canActivate: [AuthGuard], children: [
      {path: 'regular', component: RegularComponent},
      {path: 'partner', component: PartnerComponent, canActivate: [DoctorGuard]},
    ]
  },
  // Should find a way to make this generic error page to handle pages such as 404, and forbidden
  {path: 'error', component: ErrorComponent, data: {code: 'NOT_FOUND'}},
  {path: '**', redirectTo: '/error'},

]


@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
