import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WsNotificationsComponent } from './ws-notifications.component';

describe('WsNotificationsComponent', () => {
  let component: WsNotificationsComponent;
  let fixture: ComponentFixture<WsNotificationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WsNotificationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WsNotificationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
