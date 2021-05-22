import { Observable } from 'rxjs';
import { OfertaModel } from './../models/oferta-detalhada.model';
import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OfertaDetalhadaService {

  private api = `api/ofertas`;

  constructor(private http: HttpClient) {
  }

  buscarTodos(): Observable<OfertaModel[]> {
    return this.http.get<any[]>(`${this.api}`) as Observable<OfertaModel[]>;
  }

  buscarPorId(id: number): Observable<OfertaModel> {
    return this.http.get<any>(`${this.api}/${id}`) as Observable<OfertaModel>;
  }

  salvar(oferta: OfertaModel, token: string): Observable<OfertaModel> {
    return this.http.post(`${this.api}/?token=${token}`, oferta) as Observable<OfertaModel>;
  }

  atualizar(oferta: OfertaModel): Observable<OfertaModel> {
    return this.http.put(`${this.api}`, oferta) as Observable<OfertaModel>;
  }

  excluir(id: number) {
    return this.http.delete(`${this.api}/${id}`);
  }

  aceitar(id: number) {
    return this.http.patch(`${this.api}/aceitar/${id}`, id);
  }

  recusar(id: number) {
    return this.http.patch(`${this.api}/recusar/${id}`, id);
  }

  listarOfertasDetalhadas(itens: number[]): Observable<OfertaModel[]> {
    return this.http.post(`${this.api}/oferta-detalhada`, itens) as Observable<OfertaModel[]>;
  }

}
