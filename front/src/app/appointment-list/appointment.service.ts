import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Appointment} from "./appointment.model";
import {environment} from "../../environments/environment";

const headerDict = {
  'Content-Type': 'application/json',
  'Access-Control-Allow-Origin': '*',
}

const requestOptions = {
  headers: new HttpHeaders(headerDict),
};

const URL = `${environment.appURL}/appointments`


@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  constructor(private http: HttpClient) {
  }

  approveAppointment(id: number) {
    return this.http.patch(URL + '/' + id, requestOptions)
  }

  getMyAppointments(): Observable<Appointment[]> {
    console.log("appointment url: ", URL)
    return this.http.get<Appointment[]>(URL, requestOptions)
  }

  getAppointment(id: number): Observable<Appointment> {
    return this.http.get<Appointment>(URL + "/" + id, requestOptions)
  }

  createAppointment(date: Date, doctorId: number, clinicId: number) {
    return this.http.post<any>(URL, {date: date, doctorId: doctorId, clinicId: clinicId}, requestOptions)
  }

  cancelAppointment(id: number) {
    return this.http.delete<any>(URL + "/" + id, requestOptions)
  }
}
