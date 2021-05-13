import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListagemPageComponent } from './listagem-page.component';

describe('ListagemPageComponent', () => {
  let component: ListagemPageComponent;
  let fixture: ComponentFixture<ListagemPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListagemPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListagemPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
