import { TestBed } from '@angular/core/testing';

import { LastChanceService } from './last-chance.service';

describe('LastChanceService', () => {
  let service: LastChanceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LastChanceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
