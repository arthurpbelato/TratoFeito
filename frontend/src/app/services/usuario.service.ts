import { environment } from './../../environments/environment.prod';
import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';



@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private api = environment.apiUrl;

  constructor(private http: HttpClient) { }

  buscarTodos() {
    return this.http.get<any[]>(`api/usuarios`);
  }

  buscarPorId(id) {
    return this.http.get<any>(`api/usuarios/${id}`);
  }

  salvar(usuario) { 
    return this.http.post(`api/usuarios`, usuario);
  }

  atualizar(usuario) {
    return this.http.put(`api/usuarios`, usuario); 
  }

  excluir(id) { 
    return this.http.delete(`api/usuarios/${id}`);
  }
}
