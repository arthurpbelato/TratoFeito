import { ViewChild } from '@angular/core';
import { ElementRef } from '@angular/core';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PageNotificationService } from '@nuvem/primeng-components';
import { SelectItem } from 'primeng';
import { finalize } from 'rxjs/operators';
import { ItemService } from 'src/app/service/item.service';
import { ItemModel } from '../model/item.model';

@Component({
  selector: 'app-item-cadastro',
  templateUrl: './item-cadastro.component.html'
})
export class ItemCadastroComponent implements OnInit {

  display: boolean = false;
  submit: boolean = false;
  isEditing: boolean = false;
  form: FormGroup;
  imagemUrl: string = "Selecione uma imagem";

  @ViewChild('myImageInput')
  myImageInputVariable: ElementRef;

  @Output() itemListagemOutput: EventEmitter<void> = new EventEmitter();

  categorias: SelectItem[] = [
      {label: 'Colecionável', value: 1},
      {label: 'Edição Especial', value: 2},
      {label: 'Antiguidade', value: 3},
      {label: 'Artesanal', value: 4},
      {label: 'Memorabilia', value: 5},
      {label: 'Item Autografado', value: 6},
      {label: 'Outro', value: 7}
  ];

  constructor(
    private itemService: ItemService, 
    private fb: FormBuilder,
    private notification: PageNotificationService
    ) { }

  iniciarForm() {
    this.form = this.fb.group({
      id: [null],
      nome: ['', [Validators.required]],
      descricao: ['', [Validators.required]],
      foto: [null, [Validators.required]],
      disponibilidade: [false, [Validators.required]],
      situacao: ['OK', [Validators.required]],
      idUsuario: [JSON.parse(localStorage.getItem("usuario")).id, [Validators.required]],
      idCategoria: [null, [Validators.required], {update: 'change'}]
    });
  }

  ngOnInit(): void {
    this.itemListagemOutput.emit();
    this.iniciarForm();
  }

  showDialog(){
    this.display = true;
  }

  onFileChanged(event){
    const file = event.target.files[0];
    file.name
    const reader = new FileReader();
    reader.onloadend = () => {
      let image: string = `${reader.result}`;
      this.form.patchValue({
        foto: image.split(',',2)[1]
      });
      this.imagemUrl = file.name;
    }
    reader.readAsDataURL(file);
  }

  onSubmit() {
    this.submit = true;
    if(this.isEditing){
      this.alterarItem();
    }else{
      this.salvarItem();
    }
  }

  private salvarItem(){
    this.itemService.salvar(this.form.value).pipe(
      finalize(() => {
        this.submit = false;
        this.fecharModal();
      })
    ).subscribe(
      (item) => {
        this.itemListagemOutput.emit();
        this.notification.addSuccessMessage("Item Inserido com Sucesso.");
      },
      () => {
        this.notification.addErrorMessage("Falha ao Inserir Item.");
      }
    );
  }

  private alterarItem(){
    this.itemService.alterar(this.form.value).pipe(
      finalize(() => {
        this.submit = false;
        this.fecharModal();
      })
    ).subscribe(
      () => {
        this.itemListagemOutput.emit();
        this.notification.addSuccessMessage("Item Atualizado com Sucesso.");
      },
      () => {
        this.notification.addErrorMessage("Falha ao Atualizar Item.");
      }
    );
  }

  alterar(id: number){
    this.isEditing = true;
    this.itemService.obterPorId(id).subscribe(
      (item: ItemModel) => {
        this.display = true;
        this.form.patchValue({
          ...item
        });
      },
      () => {
        this.notification.addErrorMessage("Falha ao buscar Item de ID " + id + ".");
      }
    );
  }

  fecharModal() {
    this.form.patchValue(
      {id: null, nome: '', descricao: '', foto: null, disponibilidade: false, situacao: 'OK', idUsuario: JSON.parse(localStorage.getItem("usuario")).id, idCategoria: null}
    );
    this.display = false;
    this.isEditing = false;
    this.myImageInputVariable.nativeElement.value = "";
    this.imagemUrl = "Selecione uma imagem";
  }

}
