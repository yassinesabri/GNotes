import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import {Http, Headers, RequestOptionsArgs, RequestOptions} from "@angular/http";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import {observable} from "rxjs/symbol/observable";


@Injectable()
export class EtudiantService{

  private baseUrl:string='/api';
  private headers = new Headers({'Content-Type':'application/json'});
  private options = new RequestOptions({headers: this.headers});

  constructor(private _http:Http){}

  getUser(){
    return this._http.get(this.baseUrl+"/etudiant",this.options)
      .map(res => res.json())
      .catch(this.errorHandler);
  }

  errorHandler(error:Response){
    return observable.throw(error||"SERVER ERROR");
  }
}
