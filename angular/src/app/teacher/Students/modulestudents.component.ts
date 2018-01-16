import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {LoginService} from "../../login/login.service";

import {Http, RequestOptions, Headers} from "@angular/http";
declare var jQuery:any;
let $ = jQuery;

@Component({
  selector: 'modulestudents',
  templateUrl: './modulestudents.component.html',
  styleUrls: ['./modulestudents.component.css']
})
export class ModuleStudentsComponent implements OnInit {

  numeroModule:string;
  students:any[];
  importFile : File;
  selectedYear:string="2016/2017";
  years=["2000/2001","2001/2002","2002/2003","2003/2004","2004/2005","2005/2006","2006/2007","2007/2008","2008/2009","2009/2010","2010/2011","2011/2012","2012/2013","2013/2014","2014/2015","2015/2016","2016/2017","2016/2017"];

  constructor(private route: ActivatedRoute,private http: Http, private router: Router, private loginService: LoginService) {
      //not connected
    if (!this.loginService.account) {
      this.router.navigate(['/login']);
    }
  }
  fetchStudents() {
    console.log("hola from fetch students");
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    let options = new RequestOptions();
    options.headers = headers;
    this.selectedYear=this.selectedYear.replace("/","_");
    this.http.get("/api/fetchStudentsModules/" +this.numeroModule+"/"+this.selectedYear, options)
      .map(res => res.json())
      .subscribe(student => {
        this.students = student;
        console.log(this.students);
      });
  }

  ngOnInit(){
    // this.years==["2000/2001","2001/2002","2002/2003","2003/2004","2004/2005","2005/2006","2006/2007","2007/2008",];
    this.route.params.subscribe(params => {
        this.numeroModule=params['numero']


        console.log(this.numeroModule);
        // console.log(this.filiere);

       this.fetchStudents();
      }
    );
  }

  updateStudents(selectedValue) {
    this.selectedYear=selectedValue;
    this.fetchStudents();
  }

  fileChange(event:any){
    let fileList : FileList = event.target.files;
    if(fileList.length > 0){
        this.importFile = fileList[0];
    }
  }

  import(){
    if(this.importFile != null){
      $("#loading").show();
      let formData : FormData = new FormData();
      formData.append('uploadFile', this.importFile, this.importFile.name);

      let headers = new Headers();
      headers.append('Accept', 'application/json');
      let options = new RequestOptions({headers: headers});

      this.http.post("api/importTeacherFile",formData,options)
        .map(res => res.text())
        .subscribe(res => {
            $("#loading").hide();
            console.log(res);
          this.fetchStudents();
            this.notifyMessage(res);
          }

        );
    }else{
      this.notifyMessage("Veuillez choisir un fichier excel");
    }
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
