import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {Http} from "@angular/http";
import {LoginService} from "../../login/login.service";

@Component({
  selector: 'app-teachers',
  templateUrl: './teachers.component.html',
  styleUrls: ['./teachers.component.css']
})
export class TeachersComponent implements OnInit {

  constructor(private http:Http, private router: Router, private loginService:LoginService) {
    //not connected
    if(!this.loginService.account){
      this.router.navigate(['/login']);
    }
  }

  ngOnInit() {
  }

}
