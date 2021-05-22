import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ItemModel } from '../models/item.model';

@Injectable({
    providedIn: 'root'
})
export class ItemService {

    private api: string = `${environment.apiUrl}/itens`;

    constructor(private http: HttpClient) { }

    listar(): Observable<ItemModel[]> {
        return this.http.get(this.api) as Observable<ItemModel[]>;
    }

    obterPorId(id: number): Observable<ItemModel> {
        return this.http.get(`${this.api}/${id}`) as Observable<ItemModel>;
    }

    salvar(item: ItemModel): Observable<ItemModel> {
        return this.http.post(`${this.api}`, item) as Observable<ItemModel>;
    }

    alterar(item: ItemModel): Observable<ItemModel> {
        return this.http.post(`${this.api}`, item) as Observable<ItemModel>;
    }

    deletar(id: number) {
        return this.http.delete(`${this.api}/${id}`);
    }

    disponibilizar(id: number, disponibilidade: boolean): Observable<ItemModel> {
        return this.http.patch(`${this.api}/disponibilidade/${id}/${disponibilidade}`, null) as Observable<any>;
    }

    getItemDetalhado(id: number): Observable<ItemModel> {
        return this.http.get(`${this.api}/item-detalhado/${id}`) as Observable<ItemModel>;
    }

    listarItemDetalhado(): Observable<ItemModel[]> {
        return this.http.get(`${this.api}/item-detalhado`) as Observable<ItemModel[]>;
    }

    listarItemDetalhadoUsuario(id: number): Observable<ItemModel[]> {
        return this.http.get(`${this.api}/item-detalhado-usuario/${id}`) as Observable<ItemModel[]>;
    }

    listarItemCategoriaExcetoUsuarioLogado(categoriaId: number, usuarioId: number): Observable<ItemModel[]> {
        return this.http.get(`${this.api}/item-categoria-except-usuario/${categoriaId}/${usuarioId}`) as Observable<ItemModel[]>;
    }
}