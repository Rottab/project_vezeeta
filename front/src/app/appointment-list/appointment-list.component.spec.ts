import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AppointmentListComponent} from './appointment-list.component';
import {AuthService} from "../auth/auth.service";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {RouterTestingModule} from "@angular/router/testing";
import {AppointmentService} from "./appointment.service";

describe('AppointmentListComponent', () => {
  let component: AppointmentListComponent;
  let fixture: ComponentFixture<AppointmentListComponent>;
  let authServiceStub: Partial<AuthService> = {
    hasRole(role: string): boolean {
      return true
    }
  }
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppointmentListComponent ],
      providers: [{provide: AuthService, useValue: authServiceStub}, AppointmentService],
      imports: [HttpClientTestingModule, RouterTestingModule]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppointmentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
