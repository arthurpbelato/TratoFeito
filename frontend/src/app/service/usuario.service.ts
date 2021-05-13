import { environment } from './../../environments/environment.prod';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private api = `${environment.apiUrl}/usuarios`;

  constructor(private http: HttpClient) { 
    
  }

  buscarTodos(){
    return this.http.get<any[]>(`api/usuarios`);
  }

  salvar(usuario){
    return this.http.post(`api/usuarios`, usuario);
  }

  atualizar(usuario){
    return this.http.put(`api/usuarios`, usuario);
  }

  excluir(id){
    return this.http.delete(`api/usuarios/${id}`);
  }
}
