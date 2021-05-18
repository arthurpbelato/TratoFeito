import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListagemPageComponent } from './listagem-page/listagem-page.component';


const routes: Routes = [
  { path: '', component:  ListagemPageComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsuarioRoutingModule { }
