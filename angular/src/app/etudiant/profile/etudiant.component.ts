import { Component, OnInit } from '@angular/core';
import { LoginService } from "../../login/login.service";
import {Headers, Http, RequestOptions} from "@angular/http";

import{Etudiant} from "../../utils/etudiant";
import {Router} from "@angular/router";

declare var jQuery:any;
let $ = jQuery;

@Component({
  selector: 'etudiant',
  templateUrl: './etudiant.component.html',
  styleUrls: ['./etudiant.component.css']
})
export class EtudiantComponent implements OnInit {

  etudiant:Etudiant;


  constructor(private _loginService:LoginService, private router: Router,private http:Http) {
    //not connected
    if(!this._loginService.account){
      this.router.navigate(['/login']);
    }
  }
  nom:string="test";
  ngOnInit() {
    // console.log("test"+this._loginService.account.nom)
    this.etudiant=this._loginService.account;
    if(Object.keys(this.etudiant).length<10){
      this.etudiant = JSON.parse(localStorage.getItem('User'));
    }else{
      localStorage.setItem('User', JSON.stringify(this.etudiant));
    }
    console.log(this.etudiant);
  }

  updateUser(){
    let headers = new Headers();
    headers.append('Content-Type','application/json');
    let options = new RequestOptions({headers : headers});
    options.headers = headers;
    console.log(this.etudiant);
    options.body = JSON.stringify(this.etudiant);
    this.http.put("/api/updateStudent", options.body, options)
      .map(res => res.text())
      .subscribe(res => {
        console.log(res);
        this.notifyMessage(res);
      });
    console.log("ok I guess");
  }

  notifyMessage(message:string){
    if(message.indexOf("OK") !== -1){
      $.notify(message, {
        type: 'success',
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
    }else{
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
}
