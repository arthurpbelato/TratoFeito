import { Observable } from 'rxjs';
import { UsuarioModel } from './../usuario/models/usuario.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  login(credentials): Observable<UsuarioModel>{
    return this.http.post<UsuarioModel>(`api/login`, credentials);
  }
}
