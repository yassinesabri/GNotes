import {Component, OnInit} from '@angular/core';
import {Headers, Http, RequestOptions} from "@angular/http";
import {Router} from "@angular/router";
import {LoginService} from "../../../login/login.service";

declare var jQuery:any;
let $ = jQuery;

@Component({
  selector: 'app-update-teachers',
  templateUrl: './update-teachers.component.html',
  styleUrls: ['./update-teachers.component.css']
})
export class UpdateTeachersComponent implements OnInit {

  teachers:any;
  searchFilter:string;

  constructor(private http:Http, private router: Router, private loginService:LoginService) {
    //not connected
    if(!this.loginService.account){
      this.router.navigate(['/login']);
    }
  }

  ngOnInit() {
    this.fetchTeachers();
  }

  fetchTeachers(){
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    let options = new RequestOptions({headers : headers});
    let search = this.searchFilter ? this.searchFilter : "nothing"
    this.http.get("/api/fetchTeachers/"+search, options)
      .map(res => res.json())
      .subscribe(teachers => {
        console.log(teachers);
        this.teachers = teachers;
      });
  }

  onSelectTeacher(event:any, numero:string){
    event.preventDefault();
    this.router.navigate(["/updateTeacher",numero]);

  }

  deleteTeacher(event:any, id:string){
    event.preventDefault();
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    let options = new RequestOptions({headers : headers});
    this.http.delete("/api/deleteTeacher/"+id, options)
      .map(res => res.text())
      .subscribe(res => {
        console.log(res);
        this.fetchTeachers();
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
