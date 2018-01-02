import { Component, OnInit } from '@angular/core';
import {Http, RequestOptions, RequestOptionsArgs, Headers} from "@angular/http";

@Component({
  selector: 'app-update-students',
  templateUrl: './update-students.component.html',
  styleUrls: ['./update-students.component.css']
})
export class UpdateStudentsComponent implements OnInit {

  students:any;
  searchFilter:string;

  constructor(private http:Http) { }

  ngOnInit() {
    this.fetchStudents();
  }

  fetchStudents(){
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    let options = new RequestOptions({headers : headers});
    let search = this.searchFilter ? this.searchFilter : "nothing"
    this.http.get("/api/fetchStudents/"+search, options)
      .map(res => res.json())
      .subscribe(students => {
        console.log(students);
        this.students = students;
      });
  }
  onSelectStudent(event:any, numero:string){
    event.preventDefault();

  }
}
