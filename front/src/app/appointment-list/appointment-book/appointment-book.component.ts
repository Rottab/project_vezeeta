import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AppointmentService} from "../appointment.service";
import {Schedule} from "../../doctor-list/doctor/schedule/schedule.model";
import {Appointment} from "../appointment.model";
import {DoctorService} from "../../doctor-list/doctor.service";
import {Observable, Subscription} from "rxjs";
import {FormControl, FormGroup, Validators} from "@angular/forms";

const DAYS = ['MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'];

@Component({
  selector: 'app-appointment-book',
  templateUrl: './appointment-book.component.html',
  styleUrls: ['./appointment-book.component.css']
})
export class AppointmentBookComponent implements OnInit, OnDestroy {

  appointmentSubscription!: Subscription
  doctorAppointmentList: Appointment[] = [] as Appointment[]
  scheduleList: Schedule[] = [] as Schedule[]
  doctorId: number
  clinicId: number // need to find a better approach to this
  bookForm!: FormGroup

  constructor(private appointmentService: AppointmentService,
              private doctorService: DoctorService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.doctorId = +this.activatedRoute.snapshot.queryParams["doctorId"]
    this.appointmentSubscription = this.doctorService.getAvailability(this.doctorId).subscribe((response) => {
      this.doctorAppointmentList = response['appointmentList']
      this.scheduleList = response['scheduleList']
    }, error => {
      this.router.navigate(['error'])
    })

    this.bookForm = new FormGroup({
      'date': new FormControl(null, [Validators.required], [this.validateDate.bind(this)]),
      'time': new FormControl("12:00:00", [Validators.required], [this.validateTime.bind(this)])
    })
  }

  ngOnDestroy(): void {
    if (this.appointmentSubscription)
      this.appointmentSubscription.unsubscribe()
  }

  onSubmit() {
    let date = new Date(this.bookForm.value['date'] + 'T' + this.bookForm.value['time'])
    this.appointmentService.createAppointment(date, this.doctorId, this.clinicId).subscribe((response) => {
      this.router.navigate(['appointments', response.id])
    }, (error) => {
      // Something more than just this needs to be done
      console.log(error)
      this.bookForm.reset()
    })
  }

  validateDate(control: FormControl): Promise<any> | Observable<any> {
    return new Promise<any>((resolve, reject) => {
      let day = new Date(control.value).getDay() - 1
      let res = this.scheduleList.some(((schedule) => {
        return schedule.dayOfWeek === DAYS[day]
      }))
      if (res) resolve(null)
      else resolve({'INVALID_DAY': true})
    })

  }

  private addMinutes(date: Date, minutes: number) {
    return new Date(date.getTime() + minutes * 60000).getTime()
  }

  // Not fully tested
  private validateTime(control: FormControl): Promise<any> | Observable<any> {
    return new Promise<any>((resolve, reject) => {
      let date = this.bookForm.get('date').value
      if (date === null) resolve({'NULL_DATE': true})
      let selectedTime = control.value
      let day = new Date(date).getDay() - 1
      let schedule: Schedule = this.scheduleList.find((schedule) => {
        return schedule.dayOfWeek === DAYS[day]
      })
      let timeFrom = new Date(date + "T" + schedule.timeFrom)
      let timeTo = new Date(date + "T" + schedule.timeTo)
      let time = new Date(date + "T" + selectedTime)
      if (!(timeFrom < time && time < timeTo)) resolve({'INVALID_TIME': true})
      let appointments: Appointment[] = this.doctorAppointmentList.filter((appointment) => {
        return new Date(appointment.date).getDay() === time.getDay()
      })
      let appointmentConflict = appointments.some((appointment) => {
        let appointmentDate = new Date(appointment.date)
        if (
          (time.getTime() < appointmentDate.getTime() && this.addMinutes(time, 30) > appointmentDate.getTime())
          ||
          (time.getTime() < appointmentDate.getTime() && this.addMinutes(appointmentDate, 30) > time.getTime())
          ||
          (time.getTime() === appointmentDate.getTime() && this.addMinutes(appointmentDate, 30) === this.addMinutes(time, 30))
        ) {
          return true
        }
        return false
      })
      if (appointmentConflict) if (!(timeFrom < time && time < timeTo)) resolve({'TIME_CONFLICT': true})
      this.clinicId = this.scheduleList.find((schedule) => {
        return DAYS[day] === schedule.dayOfWeek
      }).clinic.id
      resolve(null)
    })

  }
}
