import {Component, OnDestroy, OnInit} from '@angular/core';
import {Appointment} from '../appointment.model';
import {AppointmentService} from "../appointment.service";
import {Subscription} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../auth/auth.service";

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.css']
})
export class AppointmentComponent implements OnInit, OnDestroy {

  appointment: Appointment = {} as Appointment
  appointmentSubscription!: Subscription
  approveSubscription!: Subscription
  cancelSubscription!: Subscription
  isDoctor!: boolean

  constructor(private appointmentService: AppointmentService, private authService: AuthService, private router: Router, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    let appointmentId = this.activatedRoute.snapshot.params['id'] || undefined;
    this.appointmentSubscription = this.appointmentService.getAppointment(appointmentId).subscribe(
      response => this.appointment = response,
      error => this.router.navigate(['error'])
    )
    this.isDoctor = this.authService.hasRole("ROLE_DOCTOR")
  }

  ngOnDestroy(): void {
    if (this.appointmentSubscription)
      this.appointmentSubscription.unsubscribe()
  }
  approveAppointment(id: number) {
    this.approveSubscription = this.appointmentService.approveAppointment(id).subscribe((response) => {
      this.appointment.approved = true
    }, (error) => {
      // Need to do something to handle errors
      console.log("error", error)
    })
  }
  cancelAppointment(id: number) {
    this.cancelSubscription = this.appointmentService.cancelAppointment(id).subscribe((response) => {
      console.log(response)
      this.router.navigate(['appointments'])
    }, (error) => {
      // Need to do something to handle errors
      console.log("error", error)
    })
  }

}
