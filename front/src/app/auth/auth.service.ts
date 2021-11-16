import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {AuthRepository} from './signin/auth.repository';
import {BehaviorSubject, Observable, throwError} from 'rxjs';
import {User} from "src/app/auth/user.model";
import {catchError, tap} from "rxjs/operators";
import {environment} from "../../environments/environment";

const SIGNIN_URL = `${environment.appURL}/auth/login`
const SIGNUP_URL = `${environment.appURL}/auth/signup`

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  user = new BehaviorSubject<User | null>(null)
  private tokenExpiration: any

  constructor(private http: HttpClient, private router: Router) {
  }

  hasRole(role: string): boolean {
    return this.user.value.roles.includes(role)
  }


  signinErrorHandler(error: HttpErrorResponse) {
    let errorMessage = "An unknown error occurred"
    switch (error.error.status) {
      case 403:
        errorMessage = "Invalid username or password"
        break
    }
    return throwError(errorMessage)
  }

  singupErrorHandler(error: HttpErrorResponse) {
    let errorMessage = "An unknown error occurred"
    switch (error.error.message) {
      case "username_used":
        errorMessage = "Username already exits"
        break
      case "email_used":
        errorMessage = "Email already been used"
        break
    }
    return throwError(errorMessage)
  }

  login(username: string, password: string) {
    return this.http.post<AuthRepository>(SIGNIN_URL, {
        username: username,
        password: password
      }
    ).pipe(catchError(this.signinErrorHandler), tap({
      next: (response) => {
        this.handleAuthentication(response.username, response.token, response.type, response.expiresIn, response.roles)
        this.autoLogout(response.expiresIn)
      }
    }))
  }

  autoLogout(expirationIn: number) {
    this.tokenExpiration = setTimeout(() => {
      this.logout();
    }, expirationIn)
  }

  logout() {
    localStorage.removeItem("userData")
    this.user.next(null);
    this.router.navigate(["/auth"])
  }

  autoLogin() {
    const user: AuthRepository = JSON.parse(<string>localStorage.getItem('userData'))
    if (!user) {
      return;
    }
    let loadedUser = new User(user.username, user.token, user.type, user.expiresIn, user.roles)
    if (loadedUser.token) {
      this.user.next(loadedUser)
      const expirationDate = new Date(user.expiresIn).getTime() - new Date().getTime()
      this.autoLogout(expirationDate)
    }
  }


  private handleAuthentication(username: string, token: string, type: string, expiresIn: number, roles: string[]) {
    const expirationDate = new Date(new Date().getTime() + expiresIn)
    const user = new User(username, token, type, expirationDate.getTime(), roles)
    this.user.next(user);
    localStorage.setItem('userData', JSON.stringify(user))
  }

  signup(username: string, firstName: string, lastName: string, email: string, password: string) {
    return this.http.post(SIGNUP_URL, {
        username: username,
        firstName: firstName,
        lastName: lastName,
        email: email,
        password: password
      }
    ).pipe(catchError(this.singupErrorHandler))
  }
}

