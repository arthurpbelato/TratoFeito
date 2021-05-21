import { UsuarioService } from './../../service/usuario.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PageNotificationService } from '@nuvem/primeng-components';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-listagem-usuario',
  templateUrl: './listagem-usuario.component.html',
  styleUrls: ['./listagem-usuario.component.css']
})
export class ListagemUsuarioComponent implements OnInit {

  usuarios: any[] = [];
  displayModal: boolean = false
  submit: boolean = false
  isEditing: boolean = false
  form: FormGroup

  constructor(
    private usuarioService: UsuarioService,
    private fb: FormBuilder,
    private notification: PageNotificationService
  ) { }

  ngOnInit(): void {
    this.buscarTodos();
    this.iniciarForm();
  }

  iniciarForm(){
    this.form = this.fb.group({
      id: [null],
      nome: [null, [Validators.required]],
      email: [null, [Validators.required, Validators.email]],
      cpf: [null, [Validators.required, Validators.maxLength(11), Validators.minLength(11)]],
      dataNascimento: [null, [Validators.required]]
    });
  }

  buscarTodos() {
    this.usuarioService.buscarTodos().subscribe(
      (usuarios) => {
        this.usuarios = usuarios;
      }
    )
  }

  salvarUsuario(){
    this.submit = true;

    if (this.isEditing){
      this.usuarioService.atualizar(this.form.value).pipe(
        finalize(() => {
          this.submit = false;
          this.fecharModal()
      })).subscribe(
        () => {
          this.buscarTodos();
          this.notification.addSuccessMessage('Usuario alterado com sucesso!');
        },
        () => {
          this.notification.addErrorMessage('Erro ao alterar usuario!')
        }
      ) 
    } else {
      this.usuarioService.salvar(this.form.value).pipe(
        finalize(() => {
          this.submit = false
          this.fecharModal()
        })
      ).subscribe(
        () => {
          this.buscarTodos();
          this.fecharModal();
          this.notification.addSuccessMessage('Usuario cadastrado com sucesso!');
        },
        () => {
          this.notification.addErrorMessage('Erro ao cadastrar usuario!')
        }
      );
    }
  }

  excluirUsuario(id){
    this.usuarioService.excluir(id).subscribe(
      () => {
        this.buscarTodos();
        this.notification.addSuccessMessage('Usuario excluido com sucesso!');
      },
      () => {
        this.notification.addErrorMessage('Erro ao excluir usuario!')
      }
    )
    console.log(id)
  }

  editarUsuario(id){
    this.isEditing = true;
    this.usuarioService.buscarPorId(id).subscribe(
      (usuario) => {
        this.displayModal = true;
        this.form.patchValue({
          ...usuario,
          dataNascimento: new Date(usuario.dataNascimento)
        })
      }
    )
    console.log(id)
  }

  abrirModal (){
    this.displayModal = true;
  }

  fecharModal(){
    this.form.reset();
    this.displayModal = false;
    this.isEditing = false;
  }




}
