import { TestBed } from '@angular/core/testing';

import { BidService } from './bid.service';

describe('BidServiceService', () => {
  let service: BidService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BidService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
