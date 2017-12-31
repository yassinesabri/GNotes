import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule} from "@angular/router";
import {HttpModule} from "@angular/http";
import {AngularFontAwesomeModule} from "angular-font-awesome";
import {DashboardComponent} from './dashboard/dashboard.component';
import {ImportAdminComponent} from './admin/import-admin/import-admin.component';
import {routes} from "./app.routes";
import { LoginComponent } from './login/login.component';
import {LoginService} from "./login/login.service";
import {FormsModule} from "@angular/forms";
@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    ImportAdminComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    HttpModule,
    AngularFontAwesomeModule,
    RouterModule.forRoot(routes),
    FormsModule
  ],
  providers: [LoginService],
  bootstrap: [AppComponent]
})
export class AppModule { }
