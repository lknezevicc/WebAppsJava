import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RestaurantListMichelinFilterComponent } from './restaurant-list-michelin-filter.component';

describe('RestaurantListMichelinFilterComponent', () => {
  let component: RestaurantListMichelinFilterComponent;
  let fixture: ComponentFixture<RestaurantListMichelinFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RestaurantListMichelinFilterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RestaurantListMichelinFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
