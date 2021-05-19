import { ItemModel } from './../models/item.model';
import { Component, OnInit } from '@angular/core';
import { Footer } from 'primeng';

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
        foto : "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWMQWeDwHwADtAH0v+zydgAAAABJRU5ErkJggg==",
        disponibilidade : true,
        situacao : "situacao",
        usuario : "usuario",
        categoria : "categoria"
      })
    }
  }

  get converterImagem() {
    
    return ;
  }

}
