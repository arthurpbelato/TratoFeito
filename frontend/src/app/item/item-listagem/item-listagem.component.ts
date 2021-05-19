import { Component, OnInit } from '@angular/core';
import { ItemModel } from '../model/item.model';
import { ItemService } from '../service/item.service';

@Component({
  selector: 'app-item-listagem',
  templateUrl: './item-listagem.component.html',
  styleUrls: ['./item-listagem.component.css']
})
export class ItemListagemComponent implements OnInit {

  itens: ItemModel[];

  constructor(private itemService: ItemService) {}

  ngOnInit(): void {
    this.itemService.listar().subscribe(
      (itens) => {
        this.itens = itens;
      }
    );
  }

}