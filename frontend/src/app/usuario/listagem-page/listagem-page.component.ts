import { UsuarioService } from './../../service/usuario.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PageNotificationService } from '@nuvem/primeng-components';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-listagem-page',
  templateUrl: './listagem-page.component.html',
  styleUrls: ['./listagem-page.component.css']
})
export class ListagemPageComponent implements OnInit {

  usuarios: any[] = [];
  displayModal: boolean = false;
  form: FormGroup;
  submit: boolean = false;
  isEdit: boolean = false;

  constructor(private usuarioService: UsuarioService,
    private fb: FormBuilder,
    private notification: PageNotificationService) {
  }

  ngOnInit(): void {
    this.buscarTodos();
    this.iniciarForm();
  }

  abrirModal() {
    this.displayModal = true;
  }

  buscarTodos() {
    this.usuarioService.buscarTodos().subscribe(
      (usuarios) => {
        this.usuarios = usuarios;

      }
    )
  }

  salvar() {
    if (this.isEdit) {
      this.usuarioService.atualizar(this.form.value).pipe(
        finalize(() => {
          this.submit = false;
          this.fecharModal();
        })
      ).subscribe(
        () => {
          this.notification.addSuccessMessage('Usuário atualizado com sucesso.');
          this.buscarTodos();
        },
        () => {
          this.notification.addErrorMessage('Falha ao atualizar usuário.');
        }
      );
    } else {
      this.submit = true;
      this.usuarioService.salvar(this.form.value).pipe(
        finalize(
          () => {
            this.submit = false
            this.fecharModal();
          }
        )
      ).subscribe(
        () => {
          this.notification.addSuccessMessage('Usuário cadastrado com sucesso.');
          this.buscarTodos();
        },
        () => {
          this.notification.addErrorMessage('Falha ao cadastrar usuário.');
        }
      );
    }
  }

  iniciarForm() {
    this.form = this.fb.group({
      id: [null],
      nome: [null, [Validators.required]],
      email: [null, [Validators.required, Validators.email]],
      cpf: [null, [Validators.required, Validators.maxLength(11), Validators.minLength(11)]],
      dataNascimento: [null, [Validators.required]]
    });
  }

  fecharModal() {
    this.form.reset();
    this.displayModal = false;
    this.isEdit = false;
  }

  excluirUsuario(id) {
    console.log()
    this.usuarioService.excluir(id).subscribe(
      (usuario) => {
        this.notification.addSuccessMessage('Usuário excluído com sucesso.');
        this.buscarTodos();
      },
      () => {
        this.notification.addErrorMessage('Falha ao excluir usuário.');
      }
    );
  }

  editarUsuario(id) {
    this.isEdit = true;
    this.usuarioService.buscarPorId(id).subscribe(
      (usuario) => {
        this.displayModal = true;
        this.form.patchValue({
          ...usuario,
          dataNascimento: new Date(usuario.dataNascimento)
        });
      }
    )
  }
}
