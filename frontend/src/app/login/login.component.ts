import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

import { LoginService } from '../services/login.service';
import { finalize } from 'rxjs/operators';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: FormGroup;
  submit = false

  constructor(
    private fb: FormBuilder,
    private loginService: LoginService,
    private router: Router
  ) { }

  ngOnInit() {
    this.iniciarForm();
  }

  iniciarForm() {
    this.form = this.fb.group({
      email: [null, [Validators.required, Validators.email]],
      token: [null, [Validators.required]],
    });
  }

  login() {
    this.submit = true;
    this.loginService.login(this.form.value).pipe(
      finalize(() => {
        this.submit = false;
      })
    ).subscribe(
      (data) => {
        localStorage.setItem('token', this.form.get('token').value);
        localStorage.setItem('usuario', JSON.stringify(data));
        this.router.navigate(['./admin']);
      }
    )
  }



}
