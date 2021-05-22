import { finalize } from 'rxjs/operators';
import { Component, OnInit } from '@angular/core';

import { ItemModel } from 'src/app/models/item-detalhado.model';
import { ItemDetalhadoService } from './../../service/item-detalhado.service';
import { OfertaService } from './../../service/oferta.service';
import { AuthService } from './../../service/auth.service';
import { OfertaDetalhadaService } from './../../service/oferta-detalhada.service';
import { ItemService } from 'src/app/service/item.service';
import { OfertaModel } from './../../models/oferta-detalhada.model';
import { PageNotificationService } from '@nuvem/primeng-components';


@Component({
  selector: 'app-ofertas-recebidas',
  templateUrl: './ofertas-recebidas.component.html',
  styleUrls: ['./ofertas-recebidas.component.css']
})
export class OfertasRecebidasComponent implements OnInit {

  ofertas: OfertaModel[] = [];
  itensUsuario: ItemModel[] = [];
  idItens: number[] = [];
  id: number;

  constructor(
    private ofertaDetalhadaService: OfertaDetalhadaService,
    private ofertaService: OfertaService,
    private itemDetalhadoService: ItemDetalhadoService,
    private ItemService: ItemService,
    private authService: AuthService,
    private notification: PageNotificationService
  ) { }

  ngOnInit(): void {
    this.getItensUsuarioLogado();
  }

  getItensUsuarioLogado() {
    this.id = this.authService.usuarioLogado.id;
    this.itemDetalhadoService.listarItemDetalhadoUsuario(this.id).subscribe(
      (itens) => {
        this.itensUsuario = itens;
        this.popularListaIdItensUsuario();
        this.iniciarListaOfertas();
      }
    )
  }

  popularListaIdItensUsuario() {
    this.idItens = this.itensUsuario.map(item => item.id);
  }

  iniciarListaOfertas() {
    this.ofertaDetalhadaService.listarOfertasDetalhadas(this.idItens).subscribe(
      (listaOfertas) => {
        this.ofertas = listaOfertas;
      }
    );
  }

  getItensOfertados(oferta: OfertaModel): string {
    return oferta.itensOfertados.map(item => item.nome).join(", ");
  }

  aceitar(oferta: OfertaModel) {
    this.ofertaService.aceitar(oferta.id, localStorage.getItem('token')).pipe(
      finalize(() => this.getItensUsuarioLogado())
    ).subscribe(
      () => {
        this.notification.addSuccessMessage('Oferta aceitada com sucesso!');
      },
      () => {
        this.notification.addErrorMessage('Erro ao aceitar oferta!')
      }
    )
  }

  recusar(oferta: OfertaModel) {
    this.ofertaService.recusar(oferta.id).pipe(
      finalize(() => this.getItensUsuarioLogado())
    ).subscribe(
      () => {
        this.notification.addSuccessMessage('Oferta recusada com sucesso!');
      },
      () => {
        this.notification.addErrorMessage('Erro ao recusar oferta!')
      }
    )
  }
}
