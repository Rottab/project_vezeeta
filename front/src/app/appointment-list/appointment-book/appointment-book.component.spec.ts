import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AppointmentBookComponent} from './appointment-book.component';
import {AppointmentService} from "../appointment.service";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {RouterTestingModule} from "@angular/router/testing";
import {DoctorService} from "../../doctor-list/doctor.service";
import {Observable} from "rxjs";

describe('AppointmentBookComponent', () => {
  let component: AppointmentBookComponent;
  let fixture: ComponentFixture<AppointmentBookComponent>;
  let appointmentServiceStub: Partial<AppointmentService> = {}
  let doctorServiceStub: Partial<DoctorService> = {
    getAvailability(id: number): Observable<any> {
      return new Observable<any>()
    }
  }

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AppointmentBookComponent],
      providers: [
        {provide: DoctorService, useValue: doctorServiceStub},
        {provide: AppointmentService, useValue: appointmentServiceStub}
      ],
      imports: [HttpClientTestingModule, RouterTestingModule]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppointmentBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
