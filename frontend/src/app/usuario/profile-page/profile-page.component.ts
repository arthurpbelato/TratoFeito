import { finalize } from 'rxjs/operators';
import { PageNotificationService } from '@nuvem/primeng-components';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UsuarioService } from './../../service/usuario.service';
import { UsuarioModel } from './../models/usuario.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {
  submit : boolean = false;
  form: FormGroup;
  usuario: UsuarioModel;
  displayModal : boolean = false;

  constructor(private usuarioService: UsuarioService,
    private fb: FormBuilder,
    private notification: PageNotificationService) {
  }

  ngOnInit(): void {
    this.iniciarForm();
    this.form.patchValue({
      ...this.usuarioLogado,
      dataNascimento: new Date(this.usuarioLogado.dataNascimento)
    });
    console.log(this.usuarioLogado.dataNascimento.getUTCDate);
    
  }

  get usuarioLogado(){
    const usuario = JSON.parse(localStorage.getItem('usuario')) as UsuarioModel;
    if(usuario){
        return usuario;
    }
    return new UsuarioModel(0,"");
}

salvar() {
    this.usuarioService.atualizar(this.form.value).pipe(
      finalize(() => {
        this.submit = false;
      })
    ).subscribe(
      (usuario) => {
        this.notification.addSuccessMessage('Usuário atualizado com sucesso.');
        console.log("alterando");
        localStorage.setItem('usuario', JSON.stringify(usuario));
      },
      () => {
        this.notification.addErrorMessage('Falha ao atualizar usuário.');
      }
    );
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
