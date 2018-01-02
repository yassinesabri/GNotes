import {Privilege} from "../../utils/privilege.enum";

export class Student{
  public numero:string;
  public nom:string;
  public prenom:string;
  public cin:string;
  public cne:number;
  public dateDeNaissance:string;
  public privilege:Privilege
  constructor(){
    this.privilege = Privilege.STUDENT;
  }
}
