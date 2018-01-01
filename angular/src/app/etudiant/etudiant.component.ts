import { Component, OnInit } from '@angular/core';
import { EtudiantService } from "./etudiant.service"
import { LoginService } from "../login/login.service";

import{Etudiant} from "../utils/etudiant";

@Component({
  selector: 'etudiant',
  templateUrl: './etudiant.component.html',
  styleUrls: ['./etudiant.component.css']
})
export class EtudiantComponent implements OnInit {

  etudiant:Etudiant;


  constructor(private _etudiantService:EtudiantService, private _loginService:LoginService) { }
  nom:string="test";
  ngOnInit() {
    // console.log("test"+this._loginService.account.nom)
    this.etudiant=this._loginService.account;

  // this._etudiantService.getUser().subscribe((etudiant)=>{
  //     console.log(etudiant);
  //     this.etudiant=etudiant;
  //   },(error)=>{
  //     console.log(error);
  //   }
  // );
  }

}