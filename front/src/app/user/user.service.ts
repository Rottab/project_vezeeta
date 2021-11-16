import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from './user.model';
import {environment} from "../../environments/environment";


const headerDict = {
  'Content-Type': 'application/json',
  'Access-Control-Allow-Origin': '*',
}

const requestOptions = {
  headers: new HttpHeaders(headerDict),
};

const URL = `${environment.appURL}/users`


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  getUserProfile(): Observable<User> {
    return this.http.get<User>(URL, requestOptions)
  }
}
