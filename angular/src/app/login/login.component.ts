import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {LoginService} from "./login.service";
import {LoginAccount} from "../utils/account";

declare var jQuery:any;
let $ = jQuery;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  account : LoginAccount;
  authAccount : any;
  constructor(private router:Router,public loginService:LoginService) {
    this.account = new LoginAccount();
    this.account.isStudent = true;
  }
  ngOnInit(){

  }
  authenticate() {
    console.log(this.account);
    this.loginService.authenticate(this.account.isStudent,this.account)
      .subscribe(account => {
        this.authAccount = account;
        console.log(account);
        if(account == null){
          this.notifyMessage("Login erreur : identifients incorrects");
        }else{
          if(account.privilege == "STUDENT"){
            this.router.navigate(['etudiant']);
          }
          else if(account.privilege == "TEACHER"){
            this.router.navigate([]);
          }
          else if(account.privilege == "ADMIN"){

            this.router.navigate(['adminImport']);
          }else{
            console.log("privilege inconnu");
          }
        }
      });

  }
  onRadioChange(){
    this.account.isStudent = !this.account.isStudent;
    if(this.account.isStudent){
      this.account.numero = null;
      this.account.password = null;

    }else{
      this.account.cin = null;
      this.account.cne = null;
    }
  }

  notifyMessage(message:string){
      $.notify(message, {
        type: 'danger',
        delay : 2000,
        newest_on_top: true,
        placement: {
          from: "top",
          align: "center"
        },
        animate: {
          enter: 'animated fadeInDown',
          exit: 'animated fadeOutUp'
        }
      });
  }
}
