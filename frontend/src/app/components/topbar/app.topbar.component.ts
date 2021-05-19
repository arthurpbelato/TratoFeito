import { AuthService } from './../../service/auth.service';
import { Router } from '@angular/router';
import { UsuarioModel } from './../../usuario/models/usuario.model';
import { Component } from '@angular/core';
import { Authentication, User } from '@nuvem/angular-base';
import { AdminComponent } from 'src/app/admin/admin.component';

@Component({
    selector: 'app-topbar',
    templateUrl: './app.topbar.component.html'
})
export class AppTopbarComponent {

    
    constructor(public app: AdminComponent, 
        private readonly _authentication: Authentication<User>,
        private router: Router,
        private authService: AuthService) {
    }

    get usuario() {
        return this._authentication.getUser();
    }

    isAuthenticated() {
        return this._authentication.isAuthenticated();
    }

    logout(){
        localStorage.removeItem("usuario");
        localStorage.removeItem("token");
        this.router.navigate(["login"]);
    }
}
