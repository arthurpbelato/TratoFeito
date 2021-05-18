import { Router } from '@angular/router';
import { finalize } from 'rxjs/operators';
import { PageNotificationService } from '@nuvem/primeng-components';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UsuarioService } from './../service/usuario.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cadastro-page',
  templateUrl: './cadastro-page.component.html',
  styleUrls: ['./cadastro-page.component.css']
})
export class CadastroPageComponent implements OnInit {

  
  usuarios: any[] = [];
  displayModal: boolean = false;
  form: FormGroup;
  submit: boolean = false;
  isEdit: boolean = false;

  constructor(private usuarioService: UsuarioService,
    private fb: FormBuilder,
    private notification: PageNotificationService,
    private router: Router) {
  }

  ngOnInit(): void {
    this.iniciarForm();
  }


  salvar() {
    if (this.isEdit) {
      this.usuarioService.atualizar(this.form.value).pipe(
        finalize(() => {
          this.submit = false;
        })
      ).subscribe(
        () => {
          this.notification.addSuccessMessage('Usuário atualizado com sucesso.');
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
          }
        )
      ).subscribe(
        () => {
          this.notification.addSuccessMessage('Usuário cadastrado com sucesso.');
          this.router.navigate(['login']);
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

}
