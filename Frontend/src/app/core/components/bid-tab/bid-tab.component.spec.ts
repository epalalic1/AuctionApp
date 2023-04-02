import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BidTabComponent } from './bid-tab.component';

describe('BidTabComponent', () => {
  let component: BidTabComponent;
  let fixture: ComponentFixture<BidTabComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BidTabComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BidTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
