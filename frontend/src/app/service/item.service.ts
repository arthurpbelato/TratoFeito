import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ItemModel } from '../item/model/item.model';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  private api: string = `${environment.apiUrl}/itens`;

  constructor(private http: HttpClient) { }

  listar(): Observable<ItemModel[]> {
    return this.http.get(this.api) as Observable<any>;
  }

  obterPorId(id: number): Observable<ItemModel> {
    return this.http.get(`${this.api}/${id}`) as Observable<any>;
  }

  salvar(item: ItemModel): Observable<ItemModel> {
    return this.http.post(`${this.api}`, item) as Observable<any>;
  }

  alterar(item: ItemModel): Observable<ItemModel> {
    return this.http.post(`${this.api}`, item) as Observable<any>;
  }

  deletar(id: number) {
    return this.http.delete(`${this.api}/${id}`);
  }

  disponibilizar(id: number, disponibilidade: boolean): Observable<ItemModel> {
    return this.http.patch(`${this.api}/${id}/${disponibilidade}`, null) as Observable<any>;
  }

}
