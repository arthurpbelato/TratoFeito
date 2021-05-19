import { ItemModel } from './../models/item.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  itens: ItemModel[] = [];

  constructor() { }

  ngOnInit(): void {
    this.iniciarListaItens();
  }

  iniciarListaItens() {

    for(let i = 0; i < 5; i++){
      this.itens.push({
        id : i+1,
        nome : "Item",
        descricao : "asasdas",
        foto : "foto",
        disponibilidade : true,
        situacao : "situacao",
        usuario : "usuario",
        categoria : "categoria"
      })
    }
  }

}
