import { Component, OnInit } from '@angular/core';
import {Http, Headers, RequestOptionsArgs, RequestOptions} from "@angular/http";
import 'rxjs/add/operator/map';

@Component({
  selector: 'import-file',
  templateUrl: './import-file.component.html',
  styleUrls: ['./import-file.component.css']
})
export class ImportFileComponent implements OnInit {

  importFile : File;
  constructor(private http:Http) { }

  ngOnInit() {
  }

  fileChange(event:any){
    let fileList : FileList = event.target.files;
    if(fileList.length > 0){
      this.importFile = fileList[0];
    }
  }

  import(){
    if(this.importFile != null){
      let formData : FormData = new FormData();
      formData.append('uploadFile', this.importFile, this.importFile.name);

      let headers = new Headers();
      headers.append('Accept', 'application/json');
      let options = new RequestOptions({headers: headers});

      this.http.post("api/importFile",formData,options)
        .map(res => res.text())
        .subscribe(res => {
            console.log(res);
          }
        );
    }
  }
}
