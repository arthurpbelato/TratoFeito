import { SharedModule } from './../shared/shared.module';
import { LOCALE_ID, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsuarioRoutingModule } from './usuario-routing.module';
import { ReactiveFormsModule } from '@angular/forms';
import { ProfilePageComponent } from './profile-page/profile-page.component';

@NgModule({
  declarations: [ProfilePageComponent],
  imports: [
    CommonModule,
    UsuarioRoutingModule,
    SharedModule,
    ReactiveFormsModule,
    
  ],
  providers: [{ provide: LOCALE_ID, useValue: 'pt-BR' }]
})
export class UsuarioModule { }
