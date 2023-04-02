import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SocialNetworkBtnsComponent } from './social-network-btns.component';

describe('SocialNetworkBtnsComponent', () => {
  let component: SocialNetworkBtnsComponent;
  let fixture: ComponentFixture<SocialNetworkBtnsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SocialNetworkBtnsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SocialNetworkBtnsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
