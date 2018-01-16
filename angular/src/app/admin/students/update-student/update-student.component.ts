import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Headers, Http, RequestOptions} from "@angular/http";
import {LoginService} from "../../../login/login.service";

declare var jQuery:any;
let $ = jQuery;

@Component({
  selector: 'app-update-student',
  templateUrl: './update-student.component.html',
  styleUrls: ['./update-student.component.css']
})
export class UpdateStudentComponent implements OnInit {
  studentId:string;
  student:any;

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

  constructor(private activeRouter:ActivatedRoute, private http:Http, private router: Router, private loginService:LoginService) {
    //not connected
    if(!this.loginService.account){
      this.router.navigate(['/login']);
    }
  }

  ngOnInit() {
    this.activeRouter.params.subscribe(params => {
        this.studentId = params['studentNumber'];
        //console.log(this.studentId);
        this.fetchStudent();
      }
    );
  }

  fetchStudent(){
    let headers = new Headers();
    headers.append('Content-Type','application/json');
    let options = new RequestOptions();
    options.headers = headers;
    this.http.get("/api/fetchStudent/"+this.studentId,options)
      .map(res => res.json())
      .subscribe(student => {
          this.student = student;
          console.log(this.student);
        });
  }

  updateStudent(){
    let headers = new Headers();
    headers.append('Content-Type','application/json');
    let options = new RequestOptions({headers : headers});
    options.headers = headers;
    console.log(this.student);
    options.body = JSON.stringify(this.student);
    this.http.put("/api/updateStudent", options.body, options)
      .map(res => res.text())
      .subscribe(res => {
        console.log(res);
        this.notifyMessage(res);
      });
  }
}
