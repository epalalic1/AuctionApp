import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddressCardInformationComponent } from './address-card-information.component';

describe('AddressCardInformationComponent', () => {
  let component: AddressCardInformationComponent;
  let fixture: ComponentFixture<AddressCardInformationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddressCardInformationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddressCardInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
