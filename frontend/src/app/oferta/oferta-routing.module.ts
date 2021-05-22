import { OfertasRecebidasComponent } from './ofertas-recebidas/ofertas-recebidas.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  { path: 'ofertas-recebidas', component: OfertasRecebidasComponent, data: { breadcrumb: 'ofertas-recebidas'} },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OfertaRoutingModule { }
