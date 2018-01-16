import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Http} from "@angular/http";
import {LoginService} from "../../../login/login.service";
import {Router} from "@angular/router";
import {Module} from "../Module";

declare var jQuery:any;
let $ = jQuery;

@Component({
  selector: 'app-create-module',
  templateUrl: './create-module.component.html',
  styleUrls: ['./create-module.component.css']
})
export class CreateModuleComponent implements OnInit {

  module:Module;
  semesters=['S1','S2','S3','S4','S5','S6','S7','S8','S9','S10'];
  filieres=['GI','GRT','GE','GIL'];
  cycles=['CP1','CP2','CI1','CI2','CI3'];

  @ViewChild('moduleForm', {read: ElementRef}) moduleForm:ElementRef; //Form Reference

  constructor(private http:Http, private router: Router, private loginService:LoginService) {
    //not connected
    if(!this.loginService.account){
      this.router.navigate(['/login']);
    }
    this.module = new Module();
    this.module.semestre = 'S1';
    this.module.cycle = 'CP1';
  }

  ngOnInit() {

  }

  addModule(){
    //reset filiere is cp1 or cp2
    if(this.module.cycle == 'CP1' || this.module.cycle == 'CP2'){
      this.module.filiere = null;
    }
    console.log(this.module);
    let headers = new Headers();
    headers.append('Accept', 'application/json');
    this.http.post("/api/createModule",this.module,headers)
      .map(res => res.text())
      .subscribe(res => {
        this.notifyMessage(res);
        if(res.indexOf("OK") !== -1){
          this.moduleForm.nativeElement.reset(); //reset form after the creation
          this.module = new Module();
          this.module.semestre = 'S1';
          this.module.cycle = 'CP1';
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
