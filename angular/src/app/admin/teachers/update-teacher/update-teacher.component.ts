import { Component, OnInit } from '@angular/core';
import {Headers, Http, RequestOptions} from "@angular/http";
import {ActivatedRoute, Router} from "@angular/router";
import {LoginService} from "../../../login/login.service";

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
        console.log(this.teacher);
      });
  }

  updateTeacher(){
    let headers = new Headers();
    headers.append('Content-Type','application/json');
    let options = new RequestOptions({headers : headers});
    options.headers = headers;
    options.body = JSON.stringify(this.teacher);
    this.http.put("/api/updateTeacher", options.body, options)
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
