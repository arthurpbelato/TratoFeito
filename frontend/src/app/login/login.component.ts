import { PageNotificationService } from '@nuvem/primeng-components';
import { AuthService } from './../service/auth.service';
import { finalize } from 'rxjs/operators';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService} from './../service/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: FormGroup;
  submit: boolean = false;

  constructor(private fb: FormBuilder, 
              private loginService: LoginService,
              private router: Router,
              private authService: AuthService,
              private notification: PageNotificationService) { }

  ngOnInit(): void {
    this.iniciarForm();
  }

  iniciarForm(){
    this.form = this.fb.group({
      email: [null, [Validators.required, Validators.email]],
      token: [null, [Validators.required]]
    });
  }

  login(){
    this.loginService.login(this.form.value).pipe(
      finalize(() => {
        this.submit = false;
        this.form.reset();
      })
    ).subscribe(
      (data) => {
        this.authService.guardarDadosLogin(this.form.get('token'), data);
        this.router.navigate(['admin'])
      },
      () => {
        this.notification.addErrorMessage("Informações incorretas. Falha no login");
      }
    )
  }

}
