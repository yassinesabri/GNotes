import {Privilege} from "../../utils/privilege.enum";

export class Teacher{
  public numero:string;
  public nom:string;
  public prenom:string;
  public password:string;
  public privilege:Privilege;
  public modules:any
  constructor(){
    this.privilege = Privilege.TEACHER;
    this.modules = null;
  }
}
