import { UsuarioModel } from './../usuario/models/usuario.model';
import { Observable } from 'rxjs';
import { environment } from './../../environments/environment.prod';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private api = `api/usuarios`;

  constructor(private http: HttpClient) { 
    
  }

  buscarTodos(): Observable<UsuarioModel[]>{
    return this.http.get<UsuarioModel[]>(this.api);
  }

  salvar(usuario): Observable<UsuarioModel>{
    return this.http.post(this.api, usuario);
  }

  atualizar(usuario): Observable<UsuarioModel>{
    return this.http.put(this.api, usuario);
  }

  excluir(id): Observable<UsuarioModel>{
    return this.http.delete(`${this.api}/${id}`);
  }

  buscarPorId(id): Observable<UsuarioModel>{
    return this.http.get<UsuarioModel>(`${this.api}/${id}`);
  }



}
