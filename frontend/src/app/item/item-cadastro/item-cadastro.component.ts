import { ViewChild } from '@angular/core';
import { ElementRef } from '@angular/core';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PageNotificationService } from '@nuvem/primeng-components';
import { SelectItem } from 'primeng';
import { finalize } from 'rxjs/operators';
import { AuthService } from 'src/app/service/auth.service';
import { CategoriaService } from 'src/app/service/categoria.service';
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
  fileName: string = "Selecione uma imagem";
  usuarioLogadoId: number = this.authService.usuarioLogado.id;

  @ViewChild('myImageInput')
  myImageInputVariable: ElementRef;

  @Output() itemListagemOutput: EventEmitter<void> = new EventEmitter();

  categorias: SelectItem[] = [];

  getCategorias(){
    this.categoriaService.listar().subscribe(
      (categorias) => {
        this.categorias = categorias;
      },
      () => {
        this.notification.addErrorMessage("Falha ao Carregar Categorias.");
      }
    );
  }

  constructor(
    private itemService: ItemService, 
    private categoriaService: CategoriaService,
    private authService: AuthService,
    private fb: FormBuilder,
    private notification: PageNotificationService
    ) { }

  iniciarForm() {
    this.form = this.fb.group({
      id: [null],
      nome: [null, [Validators.required]],
      descricao: [null, [Validators.required]],
      foto: [null, [Validators.required]],
      disponibilidade: [false, [Validators.required]],
      situacao: ['OK', [Validators.required]],
      idUsuario: [this.usuarioLogadoId, [Validators.required]],
      idCategoria: [null, [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.getCategorias();
    this.itemListagemOutput.emit();
    this.iniciarForm();
  }

  showDialog(){
    this.display = true;
  }

  onFileChanged(event){
    const file = event.target.files[0];
    const reader = new FileReader();
    reader.onloadend = () => {
      const image: string = `${reader.result}`;
      this.form.patchValue({
        foto: image.split(',',2)[1]
      });
      this.fileName = file.name;
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

  editandoForm(id: number){
    this.isEditing = true;
    this.itemService.obterPorId(id).subscribe(
      (item: ItemModel) => {
        this.display = true;
        this.form.patchValue({
          ...item
        });
      },
      () => {
        this.notification.addErrorMessage(`Falha ao buscar Item de ID ${id}.`);
      }
    );
  }

  fecharModal() {
    this.form.patchValue(
      {id: null, nome: null, descricao: null, foto: null, disponibilidade: false, situacao: 'OK', idUsuario: this.usuarioLogadoId, idCategoria: null}
    );
    this.display = false;
    this.isEditing = false;
    this.myImageInputVariable.nativeElement.value = "";
    this.fileName = "Selecione uma imagem";
  }

}
