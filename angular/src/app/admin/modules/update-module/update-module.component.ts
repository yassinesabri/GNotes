import { Component, OnInit } from '@angular/core';
import {Headers, Http, RequestOptions} from "@angular/http";
import {ActivatedRoute, Router} from "@angular/router";
import {LoginService} from "../../../login/login.service";
import {Module} from "../Module";

declare var jQuery:any;
let $ = jQuery;

@Component({
  selector: 'app-update-module',
  templateUrl: './update-module.component.html',
  styleUrls: ['./update-module.component.css']
})
export class UpdateModuleComponent implements OnInit {

  moduleId:string;
  module:Module;
  semesters=['S1','S2','S3','S4','S5','S6','S7','S8','S9','S10'];
  filieres=['GI','GRT','GE','GIL'];
  cycles=['CP1','CP2','CI1','CI2','CI3'];

  constructor(private activeRouter:ActivatedRoute, private http:Http, private router: Router, private loginService:LoginService) {
    //not connected
    if(!this.loginService.account){
      this.router.navigate(['/login']);
    }

  }

  ngOnInit() {
    this.activeRouter.params.subscribe(params => {
        this.moduleId = params['moduleNumber'];
        //console.log(this.studentId);
        this.fetchModule();
      }
    );
  }

  fetchModule(){
    let headers = new Headers();
    headers.append('Content-Type','application/json');
    let options = new RequestOptions();
    options.headers = headers;
    this.http.get("/api/fetchModule/"+this.moduleId,options)
      .map(res => res.json())
      .subscribe(module => {
        this.module = module;
        console.log(this.module);
      });
  }

  updateModule(){
    //reset filiere is cp1 or cp2
    if(this.module.cycle == 'CP1' || this.module.cycle == 'CP2'){
      this.module.filiere = null;
    }
    let headers = new Headers();
    headers.append('Content-Type','application/json');
    let options = new RequestOptions({headers : headers});
    options.headers = headers;
    options.body = JSON.stringify(this.module);
    this.http.put("/api/updateModule", options.body, options)
      .map(res => res.text())
      .subscribe(res => {
        console.log(res);
        this.notifyMessage(res);
      });
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
