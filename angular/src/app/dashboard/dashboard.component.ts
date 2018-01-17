import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {LoginService} from "../login/login.service";

import{Etudiant} from "../utils/etudiant";


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  loginAccount : any;
  etudiant:Etudiant;
  cycles:string[]=["Première année du Cycle Préparatoire"];

  constructor(private router:Router, private loginService:LoginService) {
    this.loginAccount = this.loginService.account;
    //not connected
    if(!this.loginAccount){
      this.router.navigate(['/login']);
    }
  }

  ngOnInit() {
    this.etudiant=this.etudiant = JSON.parse(localStorage.getItem('User'));
    if(this.etudiant.cycle==="CP1"){
      this.etudiant.filiere="none";
    }
    if(this.etudiant['cycle']==="CP2"){
      this.cycles[1]="Deuxième année du Cycle Préparatoire";
      this.etudiant.filiere="none";
    }
    if(this.etudiant['cycle']==="CI1"){
      this.cycles[1]="Deuxième année du Cycle Préparatoire";
      this.cycles[2]="Première année du Cycle d'Ingénieur";
    }
    if(this.etudiant['cycle']==="CI2"){
      this.cycles[1]="Deuxième année du Cycle Préparatoire";
      this.cycles[2]="Première année du Cycle d'Ingénieur";
      this.cycles[3]="Deuxième année du Cycle d'Ingénieur";
    }
    if(this.etudiant['cycle']==="CI3"){
      this.cycles[1]="Deuxième année du Cycle Préparatoire";
      this.cycles[2]="Première année du Cycle d'Ingénieur";
      this.cycles[3]="Deuxième année du Cycle d'Ingénieur";
      this.cycles[4]="Troisième année du Cycle d'Ingénieur";

    }
  }

  logout() {
    this.loginService.logout();
    this.router.navigate(['/login']);
  }
}
