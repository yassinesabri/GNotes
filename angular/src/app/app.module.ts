import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule} from "@angular/router";
import {HttpModule} from "@angular/http";
import {DashboardComponent} from './dashboard/dashboard.component';
import {ImportAdminComponent} from './admin/import-admin/import-admin.component';
import {routes} from "./app.routes";
import {LoginComponent} from './login/login.component';
import {EtudiantComponent} from './etudiant/etudiant.component';
import {LoginService} from "./login/login.service";
import {FormsModule} from "@angular/forms";
import {StudentsComponent} from './admin/students/students.component';
import {CreateStudentComponent} from './admin/students/create-student/create-student.component';
import {UpdateStudentsComponent} from './admin/students/update-students/update-students.component';
import {EtudiantService} from "./etudiant/etudiant.service";
import {UpdateStudentComponent} from './admin/students/update-student/update-student.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    ImportAdminComponent,
    LoginComponent,
    StudentsComponent,
    CreateStudentComponent,
    UpdateStudentsComponent,
    EtudiantComponent,
    UpdateStudentComponent,
  ],
  imports: [
    BrowserModule,
    HttpModule,
    RouterModule.forRoot(routes),
    FormsModule
  ],
  providers: [
    LoginService,
    EtudiantService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
