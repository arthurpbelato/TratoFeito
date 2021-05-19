import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ItemListagemComponent } from './item-listagem/item-listagem.component';
import { SharedModule } from '../shared/shared.module';
import { ItemRoutingModule } from './item-routing.module';
import { ItemComponent } from './item.component';

@NgModule({
  declarations: [
    ItemComponent,
    ItemListagemComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    ItemRoutingModule
  ]
})
export class ItemModule { }
