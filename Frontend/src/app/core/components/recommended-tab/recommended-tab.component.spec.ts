import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecommendedTabComponent } from './recommended-tab.component';

describe('RecommendedTabComponent', () => {
  let component: RecommendedTabComponent;
  let fixture: ComponentFixture<RecommendedTabComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecommendedTabComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecommendedTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
