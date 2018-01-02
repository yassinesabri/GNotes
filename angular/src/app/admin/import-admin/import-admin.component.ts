import {Component, OnInit} from '@angular/core';
import {Headers, Http, RequestOptions} from "@angular/http";
import 'rxjs/add/operator/map';
import {Router} from "@angular/router";
import {LoginService} from "../../login/login.service";

declare var jQuery:any;
let $ = jQuery;

@Component({
  selector: 'import-admin',
  templateUrl: './import-admin.component.html',
  styleUrls: ['./import-admin.component.css']
})
export class ImportAdminComponent implements OnInit {

  importFile : File;

  constructor(private http:Http, private router: Router, private loginService:LoginService) {
    //not connected
    if(!this.loginService.account){
      this.router.navigate(['/login']);
    }
  }

  ngOnInit() {
    console.log("dashboard loaded");
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

      this.http.post("api/importFile",formData,options)
        .map(res => res.text())
        .subscribe(res => {
            $("#loading").hide();
            console.log(res);
            this.notifyMessage(res);
          }
        );
    }else{
      this.notifyMessage("KO : Veuillez choisir un fichier excel");
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
