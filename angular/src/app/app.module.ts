import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule} from "@angular/router";
import {HttpModule} from "@angular/http";
import {AngularFontAwesomeModule} from "angular-font-awesome";
import {DashboardComponent} from './dashboard/dashboard.component';
import {ImportAdminComponent} from './admin/import-admin/import-admin.component';
import {routes} from "./app.routes";

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    ImportAdminComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    AngularFontAwesomeModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
