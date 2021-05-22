import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OfertaRoutingModule } from './oferta-routing.module';
import { OfertasRecebidasComponent } from './ofertas-recebidas/ofertas-recebidas.component';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [OfertasRecebidasComponent, OfertasRecebidasComponent],
  imports: [
    CommonModule,
    OfertaRoutingModule,
    SharedModule
  ]
})
export class OfertaModule { }
