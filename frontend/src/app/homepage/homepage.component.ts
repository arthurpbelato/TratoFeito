import { finalize } from 'rxjs/operators';
import { Component, OnInit } from '@angular/core';
import { Footer } from 'primeng';

import { OfertaModel } from './../models/oferta.model';
import { ItemModel } from '../models/item-detalhado.model';
import { OfertaService } from './../service/oferta.service';
import { UsuarioModel } from './../usuario/models/usuario.model';
import { AuthService } from './../service/auth.service';
import { PageNotificationService } from '@nuvem/primeng-components';
import { ItemDetalhadoService } from '../service/item-detalhado.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  id: number;
  usuario: UsuarioModel = new UsuarioModel();
  itensCat1: ItemModel[] = [];
  itensCat2: ItemModel[] = [];
  itensCat3: ItemModel[] = [];
  itensCat4: ItemModel[] = [];
  itensCat5: ItemModel[] = [];
  itensCat6: ItemModel[] = [];
  itensCat7: ItemModel[] = [];
  item: ItemModel = new ItemModel();
  itensUsuario: ItemModel[] = [];
  itensOfertados: ItemModel[] = [];
  oferta: OfertaModel = new OfertaModel();
  displayModal: boolean = false;
  podeOfertar: boolean = true;
  responsiveOptions;


  constructor(
    private itemService: ItemDetalhadoService,
    private ofertaService: OfertaService,
    private authService: AuthService,
    private notification: PageNotificationService
  ) { }

  ngOnInit(): void {
    this.setResponsiveOptions();
    this.iniciarListas();
    this.getItensUsuarioLogado();
  }

  setResponsiveOptions() {
    this.responsiveOptions = [
      {
        breakpoint: '1024px',
        numVisible: 3,
        numScroll: 3
      },
      {
        breakpoint: '768px',
        numVisible: 2,
        numScroll: 2
      },
      {
        breakpoint: '560px',
        numVisible: 1,
        numScroll: 1
      }
    ];
  }

  iniciarListas() {
    this.iniciarListaItensPorCategoria(1, this.itensCat1);
    this.iniciarListaItensPorCategoria(2, this.itensCat2);
    this.iniciarListaItensPorCategoria(3, this.itensCat3);
    this.iniciarListaItensPorCategoria(4, this.itensCat4);
    this.iniciarListaItensPorCategoria(5, this.itensCat5);
    this.iniciarListaItensPorCategoria(6, this.itensCat6);
    this.iniciarListaItensPorCategoria(7, this.itensCat7);
  }

  iniciarListaItensPorCategoria(categoria, itens: ItemModel[]) {
    this.itemService.listarItemCategoriaExcetoUsuarioLogado(categoria, this.authService.usuarioLogado.id).subscribe(
      (lista) => {
        lista.forEach(a => itens.push(a));
      }
    );
  }

  showModalDialog() {
    this.displayModal = true;
  }

  disposeModalDialog() {
    this.displayModal = false;
  }

  setarOferta() {
    this.oferta.idItemAlvo = this.item.id;
    this.oferta.idUsuarioOfertante = this.authService.usuarioLogado.id;
    this.oferta.idItensOfertados = [];
    this.oferta.idItensOfertados = this.itensOfertados.map(item => item.id);
  }

  ofertar() {
    this.setarOferta();
    this.salvarOferta();
  }

  salvarOferta() {
    this.ofertaService.salvar(this.oferta, localStorage.getItem('token')).pipe(
      finalize(() => {
        this.itensOfertados = [];
        this.disposeModalDialog();
      })
    ).subscribe(
      () => {
        this.notification.addSuccessMessage('Oferta cadastrada com sucesso!');
      },
      () => {
        this.notification.addErrorMessage('Erro ao cadastrar oferta!')
      }
    );
  }

  exibirItemDetalhado(id) {
    this.itemService.getItemDetalhado(id).subscribe(
      (item) => {
        this.item = item;
      }
    )
    this.showModalDialog();
  }

  getItensUsuarioLogado() {
    this.id = this.authService.usuarioLogado.id;
    this.itemService.listarItemDetalhadoUsuario(this.id).subscribe(
      (itens) => {
        this.itensUsuario = itens;
      }
    )
  }
}
