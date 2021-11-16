import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {AppointmentListComponent} from './appointment-list/appointment-list.component';
import {AppointmentComponent} from './appointment-list/appointment/appointment.component';
import {AuthComponent} from './auth/auth.component';
import {CategoryListComponent} from './category-list/category-list.component';
import {DoctorListComponent} from './doctor-list/doctor-list.component';
import {DoctorComponent} from './doctor-list/doctor/doctor.component';
import {HomeComponent} from './home/home.component';
import {AppointmentBookComponent} from './appointment-list/appointment-book/appointment-book.component';
import {HeaderComponent} from './header/header.component';
import {AppRoutingModule} from "./app-routing.module";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {ScheduleComponent} from './doctor-list/doctor/schedule/schedule.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AuthInterceptor} from "./auth/auth.interceptor";
import { UserComponent } from './user/user.component';
import { SignupComponent } from './auth/signup/signup.component';
import { SigninComponent } from './auth/signin/signin.component';
import { RegularComponent } from './user/regular/regular.component';
import { PartnerComponent } from './user/partner/partner.component';
import { ErrorComponent } from './error/error.component';
import { FooterComponent } from './footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    AppointmentListComponent,
    AppointmentComponent,
    AuthComponent,
    CategoryListComponent,
    DoctorListComponent,
    DoctorComponent,
    HomeComponent,
    AppointmentBookComponent,
    HeaderComponent,
    ScheduleComponent,
    UserComponent,
    SignupComponent,
    SigninComponent,
    RegularComponent,
    PartnerComponent,
    ErrorComponent,
    FooterComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
