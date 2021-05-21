import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OfertaRoutingModule } from './oferta-routing.module';
import { OfertarComponent } from './ofertar/ofertar.component';
import { SharedModule } from 'primeng';


@NgModule({
  declarations: [OfertarComponent],
  imports: [
    CommonModule,
    OfertaRoutingModule,
    SharedModule
  ]
})
export class OfertaModule { }
