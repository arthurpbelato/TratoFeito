import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { OfertarComponent } from './ofertar/ofertar.component';


const routes: Routes = [
  { path: 'ofertar', component: OfertarComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OfertaRoutingModule { }
