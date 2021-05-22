import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { SelectItem } from 'primeng';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  private api: string = `${environment.apiUrl}/categorias`;

  constructor(private http: HttpClient) { }

  listar(): Observable<SelectItem[]> {
    return this.http.get(this.api) as Observable<any>;
  }

}
