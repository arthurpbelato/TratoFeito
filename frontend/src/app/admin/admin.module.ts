import { VersionTagModule } from '@nuvem/angular-base';
import { AppFooterComponent } from './../components/footer/app.footer.component';
import { AppTopbarComponent } from './../components/topbar/app.topbar.component';
import { BlockUIModule } from 'ng-block-ui';
import { BreadcrumbModule, MenuModule, PageNotificationModule } from '@nuvem/primeng-components';
import { SharedModule } from './../shared/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HomepageModule } from '../homepage/homepage.module';

@NgModule({
  declarations: [
    AdminComponent,
    AppTopbarComponent,
    AppFooterComponent,
  ],
  imports: [
    CommonModule,
    PageNotificationModule,
    VersionTagModule,
    AdminRoutingModule,
    SharedModule,
    BreadcrumbModule,
    MenuModule,
    BlockUIModule.forRoot({
      message: "Carregando..."
    }),
    HomepageModule
  ]
})
export class AdminModule { }
