import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroPageComponent } from './cadastro-page.component';

describe('CadastroPageComponent', () => {
  let component: CadastroPageComponent;
  let fixture: ComponentFixture<CadastroPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CadastroPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastroPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
