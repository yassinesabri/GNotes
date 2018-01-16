import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {LoginService} from "../../login/login.service";

import {Http, RequestOptions, Headers} from "@angular/http";


@Component({
  selector: 'teacher',
  templateUrl: './teacher.component.html',
  styleUrls: ['./teacher.component.css']
})
export class TeacherComponent implements OnInit {
  modules:any[];
  teacher:any;

  constructor(private http:Http, private router: Router, private loginService:LoginService) {
    //not connected
    if(!this.loginService.account){
      this.router.navigate(['/login']);
    }

  }
  fetchModules() {
    this.teacher = JSON.parse(localStorage.getItem('teacher'));
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    let options = new RequestOptions();
    options.headers = headers;
    this.http.get("/api/fetchTeacherModules/" +this.teacher.numero, options)
      .map(res => res.json())
      .subscribe(module => {
        this.modules = module;
        console.log(this.modules);
      });
  }



  ngOnInit() {
    console.log("hey from techer component");
    console.log(this.loginService.account);
    this.fetchModules();
  }

}
