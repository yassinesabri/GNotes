import {Routes} from "@angular/router";
import {ImportAdminComponent} from "./admin/import-admin/import-admin.component";
import {LoginComponent} from "./login/login.component";
import {StudentsComponent} from "./admin/students/students.component";
import {CreateStudentComponent} from "./admin/students/create-student/create-student.component";
import {UpdateStudentsComponent} from "./admin/students/update-students/update-students.component";
import {EtudiantComponent} from "./etudiant/etudiant.component";
import {UpdateStudentComponent} from "./admin/students/update-student/update-student.component";
import {TeachersComponent} from "./admin/teachers/teachers.component";
import {CreateTeacherComponent} from "./admin/teachers/create-teacher/create-teacher.component";
import {UpdateTeachersComponent} from "./admin/teachers/update-teachers/update-teachers.component";
import {UpdateTeacherComponent} from "./admin/teachers/update-teacher/update-teacher.component";
import {ModulesComponent} from "./admin/modules/modules.component";
import {CreateModuleComponent} from "./admin/modules/create-module/create-module.component";
import {UpdateModulesComponent} from "./admin/modules/update-modules/update-modules.component";
import {UpdateModuleComponent} from "./admin/modules/update-module/update-module.component";

export const routes: Routes = [
  {path: '', pathMatch : 'full', redirectTo: 'login'},
  {path: 'adminImport', component: ImportAdminComponent},
  {path: 'login' , component : LoginComponent},
  {path: 'etudiant' , component : EtudiantComponent},
  {path: 'students' , component : StudentsComponent},
  {path: 'createStudent' , component : CreateStudentComponent},
  {path: 'updateStudent/:studentNumber' , component : UpdateStudentComponent},
  {path: 'updateStudents' , component : UpdateStudentsComponent},
  {path: 'teachers' , component : TeachersComponent},
  {path: 'createTeacher' , component : CreateTeacherComponent},
  {path: 'updateTeacher/:teacherNumber' , component : UpdateTeacherComponent},
  {path: 'updateTeachers' , component : UpdateTeachersComponent},
  {path: 'modules' , component : ModulesComponent},
  {path: 'createModule' , component : CreateModuleComponent},
  {path: 'updateModule/:moduleNumber' , component : UpdateModuleComponent},
  {path: 'updateModules' , component : UpdateModulesComponent},
];

