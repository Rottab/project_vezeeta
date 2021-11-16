import {Component, OnInit} from '@angular/core';
import {AuthService} from "../auth/auth.service";

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {
  userRole: boolean

  constructor(private authService: AuthService) {
  }

  ngOnInit(): void {
    this.userRole = this.authService.hasRole("ROLE_USER")
    // this.userRole = true
  }

}
