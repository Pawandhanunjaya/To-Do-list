import { TestBed } from '@angular/core/testing';

import { TaskappService } from './taskapp.service';

describe('TaskappService', () => {
  let service: TaskappService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TaskappService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
