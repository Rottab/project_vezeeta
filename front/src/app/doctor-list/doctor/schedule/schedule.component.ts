import {Component, Input, OnInit} from '@angular/core';
import {Schedule} from './schedule.model';
import {Router} from "@angular/router";

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css']
})
export class ScheduleComponent implements OnInit {
  @Input() scheduleList: Schedule[] = [];
  @Input() doctorId !: number

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  onBookAppointment() {
    this.router.navigate(['appointments', 'book'], {queryParams: {doctorId: this.doctorId}})
  }
}
