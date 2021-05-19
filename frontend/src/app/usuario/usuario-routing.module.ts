import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListagemUsuarioComponent } from './listagem-usuario/listagem-usuario.component';


const routes: Routes = [
  { path: '', component: ListagemUsuarioComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsuarioRoutingModule { }
