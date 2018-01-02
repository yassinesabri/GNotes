import {Routes} from "@angular/router";
import {ImportAdminComponent} from "./admin/import-admin/import-admin.component";
import {LoginComponent} from "./login/login.component";
import {StudentsComponent} from "./admin/students/students.component";
import {CreateStudentComponent} from "./admin/students/create-student/create-student.component";
import {UpdateStudentsComponent} from "./admin/students/update-students/update-students.component";

export const routes: Routes = [
  {path: '', pathMatch : 'full', redirectTo: 'login'},
  {path: 'login' , component : LoginComponent},
  {path: 'adminImport', component: ImportAdminComponent},
  {path: 'students' , component : StudentsComponent},
  {path: 'createStudent' , component : CreateStudentComponent},
  {path: 'updateStudents' , component : UpdateStudentsComponent}
];

