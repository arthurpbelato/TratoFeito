import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ItemListagemComponent } from './item-listagem/item-listagem.component';
import { SharedModule } from '../shared/shared.module';
import { ItemRoutingModule } from './item-routing.module';
import { ItemComponent } from './item.component';
import { ItemCadastroComponent } from './item-cadastro/item-cadastro.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    ItemComponent,
    ItemCadastroComponent,
    ItemListagemComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    ItemRoutingModule,
    ReactiveFormsModule
  ]
})
export class ItemModule { }
