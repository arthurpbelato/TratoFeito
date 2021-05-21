import { Component, ViewChild } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { ItemService } from '../service/item.service';
import { ItemCadastroComponent } from './item-cadastro/item-cadastro.component';
import { ItemModel } from './model/item.model';


@Component({
  selector: 'app-item',
  templateUrl: './item.component.html'
})
export class ItemComponent {
  
  itensCurr: ItemModel[];
  authService: AuthService = new AuthService();
  usuarioLogadoId: number = this.authService.usuarioLogado.id;

  @ViewChild(ItemCadastroComponent) itemCadastroComponent: ItemCadastroComponent;

  constructor(
    private itemService: ItemService, 
    ) {}

  buscarTodos(){
    this.itensCurr = [];
    this.itemService.listar().subscribe(
      (itens) => {
        itens.forEach((item: ItemModel)=>{
          if(item.idUsuario == this.usuarioLogadoId){
            this.itensCurr.push(item);
          }
        })
        this.showImage();
      }
    );
  }

  showImage(){
    for(var i = 0; i < this.itensCurr.length; i++){
      this.itensCurr[i].foto = "data:image/jpeg;base64," + this.itensCurr[i].foto;
    }
  }

  alterarHandler(id: number){
    this.itemCadastroComponent.editandoForm(id);
  }

}
