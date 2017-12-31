import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {LoginService} from "../login/login.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  loginAccount : any;
  constructor(private router:Router, private loginService:LoginService) {
    /*this.loginService.subscribe(account => {
      console.log("call :");
      console.log(account);
      this.loginAccount = account;
    });*/
    this.loginAccount = this.loginService.account;
  }

  ngOnInit() {

  }

  logout() {
    this.router.navigate(['/login']);
  }
}
