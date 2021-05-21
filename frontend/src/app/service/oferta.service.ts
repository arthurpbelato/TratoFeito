import { Observable } from 'rxjs';
import { OfertaModel } from './../models/oferta.model';
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

  buscarTodos(): Observable<OfertaModel[]> {
    return this.http.get<any[]>(`api/ofertas`) as Observable<OfertaModel[]>;
  }

  buscarPorId(id): Observable<OfertaModel> {
    return this.http.get<any>(`api/ofertas/${id}`) as Observable<OfertaModel>;
  }

  salvar(oferta): Observable<OfertaModel> {
    return this.http.post(`api/ofertas`, oferta) as Observable<OfertaModel>;
  }

  atualizar(oferta): Observable<OfertaModel> {
    return this.http.put(`api/ofertas`, oferta) as Observable<OfertaModel>;
  }

  excluir(id) {
    return this.http.delete(`api/ofertas/${id}`);
  }

  aceitar(id) {
    return this.http.patch(`api/ofertas/aceitar/${id}`, id);
  }

  recusar(id) {
    return this.http.patch(`api/ofertas/recusar/${id}`, id);
  }

}
