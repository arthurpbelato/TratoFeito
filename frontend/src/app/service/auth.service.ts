import { UsuarioModel } from './../usuario/models/usuario.model';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  get usuarioLogado(): UsuarioModel {
    const usuario = JSON.parse(localStorage.getItem('usuario')) as UsuarioModel;
    if (!!usuario) {
      return usuario;
    }
    return new UsuarioModel(0, "");
  }

  guardarDadosLogin(token, usuario){
    localStorage.setItem('token', token.value);
    localStorage.setItem('usuario', JSON.stringify(usuario));
  }

  atualizarUsuarioLogado(usuario){
    localStorage.setItem('usuario', JSON.stringify(usuario));
  }
}
