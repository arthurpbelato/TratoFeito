import { ItemModel } from './../../models/item.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ofertar',
  templateUrl: './ofertar.component.html',
  styleUrls: ['./ofertar.component.css']
})
export class OfertarComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    this.iniciarForm();
  }

  iniciarForm() {
    
  }

}
