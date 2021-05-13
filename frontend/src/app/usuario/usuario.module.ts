import { SharedModule } from './../shared/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsuarioRoutingModule } from './usuario-routing.module';
import { ListagemPageComponent } from './listagem-page/listagem-page.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [ListagemPageComponent],
  imports: [
    CommonModule,
    UsuarioRoutingModule,
    SharedModule,
    ReactiveFormsModule
  ]
})
export class UsuarioModule { }
