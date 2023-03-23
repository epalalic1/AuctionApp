import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BidderItemComponent } from './bidder-item.component';

describe('BidderItemComponent', () => {
  let component: BidderItemComponent;
  let fixture: ComponentFixture<BidderItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BidderItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BidderItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
