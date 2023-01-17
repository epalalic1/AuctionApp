import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LastChanceComponent } from '../last-chance/last-chance.component';
import { NewArrivalsComponent } from '../new-arrivals/new-arrivals.component';

import { TabComponent } from './tab.component';

describe('TabComponent', () => {
  let component: TabComponent;
  let fixture: ComponentFixture<TabComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TabComponent, LastChanceComponent, NewArrivalsComponent]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
