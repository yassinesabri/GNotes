import {Routes} from "@angular/router";
import {ImportAdminComponent} from "./admin/import-admin/import-admin.component";
import {LoginComponent} from "./login/login.component";

export const routes: Routes = [
  {path: '', pathMatch : 'full', redirectTo: 'login'},
  {path: 'adminImport', component: ImportAdminComponent},
  {path: 'login' , component : LoginComponent}
];

