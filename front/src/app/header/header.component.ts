import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {AuthService} from "../auth/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy {
  authSubscription!: Subscription;
  isAuthenticated = false;

  constructor(private authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
    this.authSubscription = this.authService.user.subscribe(user => {
      this.isAuthenticated = user ? true : false;
    })
  }

  ngOnDestroy(): void {
    this.authSubscription.unsubscribe()
  }


  getUsername(): string | undefined {
    if (this.authService.user)
      return this.authService.user.value?.username
    return undefined
  }

  onLogout() {
    this.authService.logout()
  }

  onUser() {
    // There has to be a better way than this
    console.log(this.authService.user.value.roles)
    let role = ''
    if (this.authService.hasRole("ROLE_DOCTOR"))
      role = 'partner'
    else if (this.authService.hasRole('ROLE_USER'))
      role = 'regular'
    this.router.navigate(['user', role])

  }
}
