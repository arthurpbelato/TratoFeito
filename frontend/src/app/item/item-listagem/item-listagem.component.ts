import { Output } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { Component, Input, OnInit } from '@angular/core';
import { PageNotificationService } from '@nuvem/primeng-components';
import { ItemService } from 'src/app/service/item.service';
import { ItemComponent } from '../item.component';
import { ItemModel } from '../model/item.model';

@Component({
  selector: 'app-item-listagem',
  templateUrl: './item-listagem.component.html',
  styleUrls: ['./item-listagem.component.css']
})
export class ItemListagemComponent implements OnInit {

  @Input() itens: ItemModel[];
  @Output() itemDeletarOutput: EventEmitter<void> = new EventEmitter();
  @Output() itemAlterarOutput: EventEmitter<number> = new EventEmitter();

  constructor(
    private itemService: ItemService, 
    private notification: PageNotificationService
    ) {}

  ngOnInit(): void {
  }

  deletar(id) {
    this.itemService.deletar(id).subscribe(
      () => {
        this.itemDeletarOutput.emit();
        this.notification.addSuccessMessage("Item Excluído com Sucesso.");
      },
      () => {
        this.notification.addErrorMessage("Falha ao Excluir Item.");
      }
    )
  }

  alterar(id) {
    this.itemAlterarOutput.emit(id);
  }

  disponibilizar(id, disponibilidade){

  }

}