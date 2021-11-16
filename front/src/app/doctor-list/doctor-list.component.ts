import {Component, OnDestroy, OnInit} from '@angular/core';
import {Doctor} from "./doctor.model";
import {Subscription} from "rxjs";
import {DoctorService} from "./doctor.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-doctor-list',
  templateUrl: './doctor-list.component.html',
  styleUrls: ['./doctor-list.component.css']
})
export class DoctorListComponent implements OnInit, OnDestroy {

  doctorList: Doctor[] = []
  private doctorSubscription!: Subscription
  private categoryId: number | undefined

  constructor(private doctorService: DoctorService, private router: Router, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.categoryId = this.activatedRoute.snapshot.queryParams['categoryId'] || undefined
    let subscribable = this.categoryId ? this.doctorService.getByCategory(this.categoryId) : this.doctorService.getAll()
    this.doctorSubscription = subscribable.subscribe(
      response => this.doctorList = response
    )
  }

  ngOnDestroy(): void {
    if (this.doctorSubscription)
      this.doctorSubscription.unsubscribe()
  }

  onDoctor(id: number) {
    this.router.navigate([id], {relativeTo: this.activatedRoute})
  }
}
