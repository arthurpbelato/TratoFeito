import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './admin.component';


const routes: Routes = [
  {
    path: '', component: AdminComponent, children: [
      { path: 'perfil', loadChildren: () => import('../usuario/usuario.module').then(m => m.UsuarioModule) },
      { path: 'homepage', loadChildren: () => import('../homepage/homepage.module').then(m => m.HomepageModule) },
      { path: 'oferta', loadChildren: () => import('../oferta/oferta.module').then(m => m.OfertaModule) },
      { path: 'itens', loadChildren: () => import('../item/item.module').then(m => m.ItemModule) }
    ]
  }]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
