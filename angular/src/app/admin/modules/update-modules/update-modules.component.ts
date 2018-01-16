import { Component, OnInit } from '@angular/core';
import {Headers, Http, RequestOptions} from "@angular/http";
import {LoginService} from "../../../login/login.service";
import {Router} from "@angular/router";

declare var jQuery:any;
let $ = jQuery;

@Component({
  selector: 'app-update-modules',
  templateUrl: './update-modules.component.html',
  styleUrls: ['./update-modules.component.css']
})
export class UpdateModulesComponent implements OnInit {

  modules:any;
  searchFilter:string;

  constructor(private http:Http, private router: Router, private loginService:LoginService) {
    //not connected
    if(!this.loginService.account){
      this.router.navigate(['/login']);
    }
  }

  ngOnInit() {
    this.fetchModules();
  }

  fetchModules(){
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    let options = new RequestOptions({headers : headers});
    let search = this.searchFilter ? this.searchFilter : "nothing"
    this.http.get("/api/fetchModules/"+search, options)
      .map(res => res.json())
      .subscribe(modules => {
        console.log(modules);
        this.modules = modules;
      });
  }
  onSelectModule(event:any, numero:string){
    event.preventDefault();
    this.router.navigate(["/updateModule",numero]);

  }

  deleteModule(event:any, id:string){
    event.preventDefault();
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    let options = new RequestOptions({headers : headers});
    this.http.delete("/api/deleteModule/"+id, options)
      .map(res => res.text())
      .subscribe(res => {
        console.log(res);
        this.fetchModules();
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
