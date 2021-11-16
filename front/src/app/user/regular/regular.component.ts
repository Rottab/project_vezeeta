import {Component, Input, OnInit} from '@angular/core';
import {User} from "../user.model";

@Component({
  selector: 'app-regular',
  templateUrl: './regular.component.html',
  styleUrls: ['./regular.component.css']
})
export class RegularComponent implements OnInit {

  @Input() user!: User

  constructor() { }

  ngOnInit(): void {
  }

}
