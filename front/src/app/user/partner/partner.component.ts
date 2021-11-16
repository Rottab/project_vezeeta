import {Component, OnDestroy, OnInit} from '@angular/core';
import { Doctor } from 'src/app/doctor-list/doctor.model';
import {DoctorService} from "../../doctor-list/doctor.service";
import {Subscription} from "rxjs";
import {AuthService} from "../../auth/auth.service";

@Component({
  selector: 'app-partner',
  templateUrl: './partner.component.html',
  styleUrls: ['./partner.component.css']
})
export class PartnerComponent implements OnInit, OnDestroy {
  doctor!: Doctor
  doctorSubscription!: Subscription

  constructor(private doctorService: DoctorService, private authService: AuthService) { }

  ngOnInit(): void {
    this.doctorSubscription = this.doctorService.getCurrentDoctor().subscribe(
      response => {
        this.doctor = response
      }
    )
  }

  ngOnDestroy(): void {
    if (this.doctorSubscription)
      this.doctorSubscription.unsubscribe()
  }

}
