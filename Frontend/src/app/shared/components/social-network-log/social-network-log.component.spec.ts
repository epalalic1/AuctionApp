import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SocialNetworkLogComponent } from './social-network-log.component';

describe('SocialNetworkLogComponent', () => {
  let component: SocialNetworkLogComponent;
  let fixture: ComponentFixture<SocialNetworkLogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SocialNetworkLogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SocialNetworkLogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
