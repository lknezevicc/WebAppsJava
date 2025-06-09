import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TopCheapestRestaurantsComponent } from './top-cheapest-restaurants.component';

describe('TopCheapestRestaurantsComponent', () => {
  let component: TopCheapestRestaurantsComponent;
  let fixture: ComponentFixture<TopCheapestRestaurantsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TopCheapestRestaurantsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TopCheapestRestaurantsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
