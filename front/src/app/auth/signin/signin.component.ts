import {Component, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  private authSubscription!: Subscription;
  error: string = ''

  constructor(private authService: AuthService, private router: Router) {
  }

  ngOnDestroy(): void {
    if (this.authSubscription)
      this.authSubscription.unsubscribe()
  }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm) {
    if (form.invalid)
      return

    const username = form.value.username
    const password = form.value.password

    this.authSubscription = this.authService.login(username, password).subscribe(
      (response) => {
        this.router.navigate(["/"])
      }, (errorMessage) => {
        this.error = errorMessage
      }
    )
    form.reset()
  }

}
