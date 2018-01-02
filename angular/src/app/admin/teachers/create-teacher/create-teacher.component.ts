import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Teacher} from "../teacher";
import {Http} from "@angular/http";
import {Router} from "@angular/router";
import {LoginService} from "../../../login/login.service";

declare var jQuery:any;
let $ = jQuery;

@Component({
  selector: 'app-create-teacher',
  templateUrl: './create-teacher.component.html',
  styleUrls: ['./create-teacher.component.css']
})
export class CreateTeacherComponent implements OnInit {

  teacher : Teacher;
  @ViewChild('teacherForm', {read: ElementRef}) teacherForm:ElementRef; //Form Reference

  constructor(private http:Http, private router: Router, private loginService:LoginService) {
    this.teacher = new Teacher();
    //not connected
    if(!this.loginService.account){
      this.router.navigate(['/login']);
    }
  }

  ngOnInit() {

  }

  addTeacher(){
    let headers = new Headers();
    headers.append('Accept', 'application/json');
    this.http.post("/api/createTeacher",this.teacher,headers)
      .map(res => res.text())
      .subscribe(res => {
        this.notifyMessage(res);
        if(res.indexOf("OK") !== -1){
          this.teacherForm.nativeElement.reset(); //reset form after the creation
        }
        console.log(res);
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
