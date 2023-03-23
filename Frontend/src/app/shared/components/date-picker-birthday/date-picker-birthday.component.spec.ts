import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DatePickerBirthdayComponent } from './date-picker-birthday.component';

describe('DatePickerBirthdayComponent', () => {
  let component: DatePickerBirthdayComponent;
  let fixture: ComponentFixture<DatePickerBirthdayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DatePickerBirthdayComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DatePickerBirthdayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
