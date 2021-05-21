import { finalize } from 'rxjs/operators';
import { Component, OnInit } from '@angular/core';
import { Footer } from 'primeng';

import { OfertaModel } from './../models/oferta.model';
import { ItemModel } from './../models/item.model';
import { OfertaService } from './../service/oferta.service';
import { ItemService } from './../service/item.service';
import { UsuarioModel } from './../usuario/models/usuario.model';
import { AuthService } from './../service/auth.service';
import { PageNotificationService } from '@nuvem/primeng-components';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  id : number;
  usuario: UsuarioModel = new UsuarioModel();
  itens: ItemModel[] = [];
  item: ItemModel = new ItemModel();
  itensUsuario: ItemModel[] = [];
  itensOfertados: ItemModel[] = [];
  oferta: OfertaModel = new OfertaModel();
  displayModal: boolean = false;


  constructor(
    private itemService: ItemService,
    private ofertaService: OfertaService,
    private authService: AuthService,
    private notification: PageNotificationService
  ) { }

  ngOnInit(): void {
    this.iniciarListaItens();
    this.getItensUsuarioLogado();
  }

  showModalDialog() {
    this.displayModal = true;
  }

  disposeModalDialog() {
    this.displayModal = false;
  }

  iniciarListaItens() {

    this.itemService.listarItemDetalhado().subscribe(
      (itens) => {
        this.itens = itens;
      }
    )
  }

  setarOferta() {
    this.oferta.idItemAlvo = this.item.id;
    this.oferta.idUsuarioOfertante = this.authService.usuarioLogado.id;
    for(let i in this.itensOfertados){
      this.oferta.idItensOfertados[i] = this.itensOfertados[i].id;
    }
  }

  ofertar() {
    this.setarOferta();
    this.salvarOferta();
  }

  salvarOferta() {
    this.ofertaService.salvar(this.oferta).pipe(
      finalize(() => {
        this.disposeModalDialog()
      })
    ).subscribe(
      () => {
        this.disposeModalDialog();
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
