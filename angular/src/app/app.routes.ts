import {Routes} from "@angular/router";
import {ImportAdminComponent} from "./admin/import-admin/import-admin.component";

export const routes: Routes = [
  {path: '', pathMatch : 'full', redirectTo: 'adminImport'},

  {path: 'adminImport', component: ImportAdminComponent}
];

