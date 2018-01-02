import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {LoginAccount} from "../utils/account";
import {Subject} from "rxjs/Subject";
import {isNull} from "util";

@Injectable()
export class LoginService{
  public account : any; //student or teacher object from server
  constructor(private http:Http){
    let login = JSON.parse(localStorage.getItem("loginAccount"));
    if(login){
      this.account = login;
    }
  }
  authenticate(isStudent:boolean, account:LoginAccount) : Observable<any> {
    let headers = new Headers();
    let url = "/api/login";
    headers.append('Content-Type', 'application/json');
    return this.http.post(url,account,headers)
      .map(response => {
          let res  = response.json();
          if(res.privilege){
            this.account = res;
            localStorage.setItem("loginAccount",JSON.stringify(this.account));
          }else{
            this.account = null;
          }
          return this.account;
      });
  }
  logout(){
    localStorage.removeItem("loginAccount");
  }
}
