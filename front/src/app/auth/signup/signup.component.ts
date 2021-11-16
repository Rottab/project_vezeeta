import {Component, OnDestroy, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit, OnDestroy {
  private authSubscription!: Subscription
  error: string = ''

  constructor(private authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    if (this.authSubscription)
      this.authSubscription.unsubscribe()
  }

  onSubmit(form: NgForm) {
    if (form.invalid)
      return

    const username = form.value.username
    const firstName = form.value.firstName
    const lastName = form.value.lastName
    const email = form.value.email
    const password = form.value.password

    this.authSubscription = this.authService.signup(username, firstName, lastName, email, password).subscribe(
      (response) => {
        this.router.navigate(["/auth/signin"])
      }, (errorMessage) => {
        this.error = errorMessage
      }
    )
    form.reset()

  }


}
