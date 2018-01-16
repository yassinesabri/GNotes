import {Injectable} from "@angular/core";
import {Headers, Http, RequestOptions} from "@angular/http";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import {Etudiant} from "../../utils/etudiant";

declare var jQuery:any;
let $ = jQuery;

@Injectable()
export class EtudiantService{

  private etudiant:Etudiant;

  constructor(private _http:Http){}

  UpdateUser(etudiant:any){
    console.log(etudiant);
    console.log("from student service");
    let headers = new Headers();
    headers.append('Content-Type','application/json');
    let options = new RequestOptions({headers : headers});
    options.headers = headers;
    options.body = JSON.stringify(this.etudiant);
    this._http.put("/api/updateStudent", options.body, options)
      .map(res => res.text())
      .subscribe(res => {
        console.log(res);
      });

    // let headers = new Headers();
    // headers.append('Content-Type','application/json');
    // let options = new RequestOptions({headers : headers});
    // options.headers = headers;
    // options.body = JSON.stringify(this.etudiant);
    // this._http.put("/api/updateStudent", options.body, options)
    //   .map(res => res.text())
    //   .subscribe(res => {
    //     console.log(res);
    //     this.notifyMessage(res);
    //   });
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
