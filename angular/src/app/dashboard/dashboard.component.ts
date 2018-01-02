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
    this.loginAccount = this.loginService.account;
    //not connected
    if(!this.loginAccount){
      this.router.navigate(['/login']);
    }
  }

  ngOnInit() {

  }

  logout() {
    this.loginService.logout();
    this.router.navigate(['/login']);
  }
}
