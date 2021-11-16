import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AppointmentComponent} from './appointment.component';
import {AppointmentService} from "../appointment.service";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {RouterTestingModule} from "@angular/router/testing";
import {AuthService} from "../../auth/auth.service";
import {BehaviorSubject, Observable} from "rxjs";
import {Appointment} from "../appointment.model";
import {User} from "../../auth/user.model";


// This isn't working at all due to the "user being undefined" thing
describe('AppointmentComponent', () => {
  let component: AppointmentComponent;
  let fixture: ComponentFixture<AppointmentComponent>;
  let appointmentServiceStub: Partial<AppointmentService> = {
    getAppointment(id: number): Observable<Appointment> {
      return new Observable<Appointment>()
    }
  }
  let testUser = new User("Ninja",
    "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJOaW5qYSIsImlkIjo1MywiZXhwIjoxNjM3MDc1MTAzLCJpYXQiOjE2MzY5ODg3MDN9.UCaEhpfgCQeXUhZu-hXAfH6a6BeArBXuM0UGTF345n6Ugk-MJvsR6cPKGawTJE7dxZCdrMmTvdgcJlmumeIj0w",
    "Bearer",
    1637075103441,
    [
      "ROLE_USER"
    ]
  )
  let authServiceStub: Partial<AuthService> = {
    hasRole(role: string): boolean {
      return true
    },
    user: new BehaviorSubject<User | null>(testUser)
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AppointmentComponent],
      providers: [
        {provide: AuthService, useValue: authServiceStub},
        {provide: AppointmentService, useValue: appointmentServiceStub}
      ],
      imports: [HttpClientTestingModule, RouterTestingModule]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
