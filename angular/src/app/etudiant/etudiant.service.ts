import {Injectable} from "@angular/core";
import {Headers, Http, RequestOptions} from "@angular/http";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';


@Injectable()
export class EtudiantService{

  private baseUrl:string='/api';
  private headers = new Headers({'Content-Type':'application/json'});
  private options = new RequestOptions({headers: this.headers});

  constructor(private _http:Http){}

  getUser(){
    return this._http.get(this.baseUrl+"/etudiant",this.options)
      .map(res => res.json());
  }
}
