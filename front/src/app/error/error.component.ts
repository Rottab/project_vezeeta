import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css']
})
export class ErrorComponent implements OnInit {

  code: string = ''
  codeSubscription: Subscription

  constructor(private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.codeSubscription = this.activatedRoute.data.subscribe((data) => {
      this.code = data['code']
    })
  }

}
