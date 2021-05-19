import { CadastroPageComponent } from './cadastro-page/cadastro-page.component';
import { AuthGuard } from './guard/auth.guard';
import { GuestGuard } from './guard/guest.guard';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DiarioErrosComponent } from './components/diario-erros/diario-erros.component';
import { LoginSuccessComponent } from '@nuvem/angular-base';

import { HomepageComponent } from './homepage/homepage.component';

const routes: Routes = [
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    { path: 'admin', loadChildren: () => import('./admin/admin.module')
    .then(m => m.AdminModule), canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent, canActivate: [GuestGuard]},
    { path: 'diario-erros', component: DiarioErrosComponent, data: { breadcrumb: 'Diário de Erros'} },
    { path: 'homepage', component: HomepageComponent },
    { path: 'login-success', component: LoginSuccessComponent},
    { path: 'cadastro', component: CadastroPageComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
