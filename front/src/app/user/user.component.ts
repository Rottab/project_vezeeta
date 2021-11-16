import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserService} from "./user.service";
import {Subscription} from "rxjs";
import {User} from "./user.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit, OnDestroy {

  user: User = {} as User
  userSubscription!: Subscription

  constructor(private userService: UserService, private router:Router) {
  }

  ngOnInit(): void {
    this.userSubscription = this.userService.getUserProfile().subscribe(
      response => this.user = response,
        error => this.router.navigate(['error'])

    )
  }

  ngOnDestroy(): void {
    if (this.userSubscription)
      this.userSubscription.unsubscribe()
  }

}
