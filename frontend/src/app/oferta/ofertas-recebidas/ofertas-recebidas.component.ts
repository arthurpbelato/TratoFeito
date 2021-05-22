import { Component, OnInit } from '@angular/core';
import { ItemModel } from 'src/app/models/item.model';

@Component({
  selector: 'app-ofertas-recebidas',
  templateUrl: './ofertas-recebidas.component.html',
  styleUrls: ['./ofertas-recebidas.component.css']
})
export class OfertasRecebidasComponent implements OnInit {

  cars: number[] = [1, 2, 3, 4, 5];
  item: ItemModel = new ItemModel();
  itens: ItemModel[] = [];

  constructor() { }

  ngOnInit(): void {

  }

}
