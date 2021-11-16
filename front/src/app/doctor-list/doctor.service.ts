import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Doctor} from "./doctor.model";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

const headerDict = {
  'Content-Type': 'application/json',
  'Access-Control-Allow-Origin': '*'
}

const requestOptions = {
  headers: new HttpHeaders(headerDict),
};

const URL = `${environment.appURL}/doctors`


@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  constructor(private http: HttpClient) {
  }

  getByCategory(id: number): Observable<Doctor[]> {
    return this.http.get<Doctor[]>(URL + '/categories/' + id, requestOptions);
  }

  getAll(): Observable<Doctor[]> {
    return this.http.get<Doctor[]>(URL, requestOptions);
  }

  getCurrentDoctor() {
    return this.http.get<Doctor>(URL + "/self", requestOptions)
  }

  getDoctor(id: number): Observable<Doctor> {
    return this.http.get<Doctor>(URL + '/' + id, requestOptions);
  }

  getAvailability(id: number) {
    return this.http.get<any>(URL + "/" + id + '/availability', requestOptions)
  }
}
