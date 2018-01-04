import { Component, OnInit } from '@angular/core';
import {Headers, Http, RequestOptions} from "@angular/http";
import {ActivatedRoute, Router} from "@angular/router";
import {LoginService} from "../../../login/login.service";
import {mod} from "ngx-bootstrap/bs-moment/utils";

declare var jQuery:any;
let $ = jQuery;

@Component({
  selector: 'app-update-teacher',
  templateUrl: './update-teacher.component.html',
  styleUrls: ['./update-teacher.component.css']
})
export class UpdateTeacherComponent implements OnInit {
  teacherId:string;
  teacher:any;
  currentModules:any;
  freeModules:any;
  constructor(private activeRouter:ActivatedRoute, private http:Http, private router: Router, private loginService:LoginService) {
    //not connected
    if(!this.loginService.account){
      this.router.navigate(['/login']);
    }
  }

  ngOnInit() {
    this.activeRouter.params.subscribe(params => {
        this.teacherId = params['teacherNumber'];
        //console.log(this.studentId);
        this.fetchTeacher();
        this.fetchFreeModules();
      }
    );
  }

  fetchTeacher(){
    let headers = new Headers();
    headers.append('Content-Type','application/json');
    let options = new RequestOptions();
    options.headers = headers;
    this.http.get("/api/fetchTeacher/"+this.teacherId,options)
      .map(res => res.json())
      .subscribe(teacher => {
        this.teacher = teacher;
        this.currentModules = this.teacher.modules
        console.log(this.teacher);
      });
  }

  fetchFreeModules(){
    let headers = new Headers();
    headers.append('Content-Type','application/json');
    let options = new RequestOptions();
    options.headers = headers;
    this.http.get("/api/fetchModules",options)
      .map(res => res.json())
      .subscribe(modules => {
        this.freeModules = modules;
        console.log(modules);
      });
  }

  onAddModule(event:any,module:any){
    this.currentModules.push(module);
    this.freeModules = this.freeModules.filter(e => e != module);
    this.sortModules();
  }

  onDeleteModule(event:any,module:any){
    this.freeModules.push(module);
    this.currentModules = this.currentModules.filter(e => e != module);
    this.sortModules();
  }

  sortModules(){
    this.currentModules.sort((m1,m2) => m1.numero > m2.numero);
    this.freeModules.sort((m1,m2) => m1.numero > m2.numero);
  }

  updateTeacher(){

    this.teacher.modules = this.currentModules;

    let headers = new Headers();
    headers.append('Content-Type','application/json');
    let options = new RequestOptions({headers : headers});
    options.headers = headers;
    options.body = JSON.stringify(this.teacher);
    this.http.put("/api/updateTeacher", options.body, options)
      .map(res => res.text())
      .subscribe(res => {
        console.log(res);
        this.updatefreeModules();
      });
  }

  updatefreeModules(){
    let headers = new Headers();
    headers.append('Content-Type','application/json');
    let options = new RequestOptions({headers : headers});
    options.headers = headers;
    options.body = JSON.stringify(this.freeModules);
    this.http.put("/api/freeModules", options.body, options)
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
