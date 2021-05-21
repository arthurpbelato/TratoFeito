import { Component, ViewChild } from '@angular/core';
import { PageNotificationService } from '@nuvem/primeng-components';
import { ItemService } from '../service/item.service';
import { ItemCadastroComponent } from './item-cadastro/item-cadastro.component';
import { ItemModel } from './model/item.model';


@Component({
  selector: 'app-item',
  templateUrl: './item.component.html'
})
export class ItemComponent {
  
  itensCurr: ItemModel[];
  @ViewChild(ItemCadastroComponent) itemCadastroComponent: ItemCadastroComponent;

  constructor(
    private itemService: ItemService, 
    private notification: PageNotificationService
    ) {}

  buscarTodosHandler(){
    this.buscarTodos();
  }

  buscarTodos(){
    this.itemService.listar().subscribe(
      (itens) => {
        this.itensCurr = itens;
        this.showImage();
      }
    );
  }

  deletar(id) {
    this.itemService.deletar(id).subscribe(
      () => {
        this.buscarTodos();
        this.notification.addSuccessMessage("Item Excluído com Sucesso.");
      },
      () => {
        this.notification.addErrorMessage("Falha ao Excluir Item.");
      }
    )
  }

  showImage(){
    for(var i = 0; i < this.itensCurr.length; i++){
      this.itensCurr[i].foto = "data:image/jpeg;base64," + this.itensCurr[i].foto;
    }
  }

  alterarHandler(id: number){
    this.itemCadastroComponent.alterar(id);
  }

}
