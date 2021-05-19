import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OfertaService {

  private api = environment.apiUrl;

  constructor(private http: HttpClient) { 
  }

  buscarTodos() {
    return this.http.get<any[]>(`api/ofertas`);
  }

  buscarPorId(id) {
    return this.http.get<any>(`api/ofertas/${id}`);
  }

  salvar(oferta) { 
    return this.http.post(`api/ofertas`, oferta);
  }

  atualizar(oferta) {
    return this.http.put(`api/ofertas`, oferta); 
  }

  excluir(id) { 
    return this.http.delete(`api/ofertas/${id}`);
  }  
}
