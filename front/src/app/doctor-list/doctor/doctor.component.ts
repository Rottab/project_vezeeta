import {Component, OnDestroy, OnInit} from '@angular/core';
import {Doctor} from "../doctor.model";
import {DoctorService} from "../doctor.service";
import {Subscription} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.css']
})
export class DoctorComponent implements OnInit, OnDestroy {

  doctor: Doctor = {} as Doctor
  private doctorSubscription: Subscription | undefined

  constructor(private doctorService: DoctorService, private router: Router, private activatedRoute: ActivatedRoute) {

  }

  ngOnInit(): void {
    //I should handle this
    let doctorId = this.activatedRoute.snapshot.params['id'] || undefined;
    this.doctorSubscription = this.doctorService.getDoctor(doctorId).subscribe(
      (response) => {
        this.doctor = response
      }, error => {
        this.router.navigate(['error'])
      }
    )
  }

  ngOnDestroy(): void {
    if (this.doctorSubscription)
      this.doctorSubscription.unsubscribe()
  }

}
