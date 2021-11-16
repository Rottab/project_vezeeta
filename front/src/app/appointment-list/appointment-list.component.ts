import {Component, OnDestroy, OnInit} from '@angular/core';
import {Appointment} from './appointment.model';
import {AppointmentService} from "./appointment.service";
import {Subscription} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../auth/auth.service";

@Component({
  selector: 'app-appointment-list',
  templateUrl: './appointment-list.component.html',
  styleUrls: ['./appointment-list.component.css']
})
export class AppointmentListComponent implements OnInit, OnDestroy {

  appointmentList: Appointment[] = []
  appointmentSubscription!: Subscription
  approveSubscription !: Subscription
  cancelSubscription !: Subscription
  isDoctor!: boolean

  constructor(private appointmentService: AppointmentService, private authService: AuthService, private router: Router, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.appointmentSubscription = this.appointmentService.getMyAppointments().subscribe((response) => {
      this.appointmentList = response
    })
    this.isDoctor = this.authService.hasRole("ROLE_DOCTOR")
  }

  ngOnDestroy(): void {
    if (this.appointmentSubscription) this.appointmentSubscription.unsubscribe()
    if (this.approveSubscription) this.approveSubscription.unsubscribe()
  }

  onAppointment(id: number) {
    this.router.navigate([id], {relativeTo: this.activatedRoute})
  }

  approveAppointment(id: number) {
    this.approveSubscription = this.appointmentService.approveAppointment(id).subscribe((response) => {
      this.appointmentList.map((appointment) => {
        if (appointment.id === id) appointment.approved = true
      })
    }, (error) => {
      // Need to do something to handle errors
      console.log("error", error)
    })
  }

  cancelAppointment(id: number) {
    this.cancelSubscription = this.appointmentService.cancelAppointment(id).subscribe((response) => {
      this.appointmentList = this.appointmentList.filter((appointment) => {
        if (appointment.id === id) return false
        return true
      })

    }, (error) => {
      // Need to do something to handle errors
      console.log("error", error)
    })
  }
}
