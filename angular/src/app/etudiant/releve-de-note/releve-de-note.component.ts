import {Component, OnInit} from "@angular/core";
import {ActivatedRoute,Params} from "@angular/router";
import {Etudiant} from "../../utils/etudiant";
import {Http,Headers , RequestOptions} from "@angular/http";
import {map} from "rxjs/operator/map";
import {Module} from "../../utils/module";



@Component({selector: 'releve-de-note',
  templateUrl: './releve-de-note.component.html',
  styleUrls: ['./releve-de-note.component.css']})
export class ReleveDeNoteComponent implements OnInit {

  etudiant:Etudiant;
  cycle: string;
  filiere: string;
  modules:Module[];
  modules2:Module[];
  notes:any[];
  commentaires:any[];
  semestre:string;


  constructor(private route: ActivatedRoute,private http:Http) {
    // console.log("cc");
  }
  fetchModules(){


      let headers = new Headers();
      headers.append('Content-Type','application/json');
      let options = new RequestOptions();
      options.headers = headers;
      let fil=this.filiere;
      console.log(this.cycle);
      if(this.cycle==="CP1" ||this.cycle==="CP2" )
        fil="none";
      this.http.get("/api/fetchStudentModules/"+this.cycle+"/"+fil+"/"+this.etudiant.numero,options)
        .map(res => res.json())
        .subscribe(module => {
           for(let testmodule of module["listeModule"]){
             if(testmodule.semestre===this.semestre){
               this.modules=module["listeModule"];
             }
           }
          this.modules=module["listeModule"];
          // this.modules2=module["listeModule"];
          this.notes=[];
          this.commentaires=new Array();
          // console.log(module["listetudiantModule"]);
          let md=module["listetudiantModule"];
          for(let modul of md){
              // console.log(modul.nomModule);
              this.notes.push(modul.note);
              this.commentaires.push(modul.commentaire)
          }
          // console.log(this.commentaires.length+" lenght");
        });

  }
  ngOnInit() {
    this.etudiant=this.etudiant = JSON.parse(localStorage.getItem('User'));
    this.route.params.subscribe(params => {
        if(params['cycle']==="Première année du Cycle Préparatoire"){
          this.cycle="CP1";
          this.semestre="S1";
        }else if(params['cycle']==="Deuxième année du Cycle Préparatoire"){
          this.cycle="CP2";
          this.semestre="S3";
        }else if(params['cycle']==="Première année du Cycle d'Ingénieur"){
          this.cycle="CI1";
          this.semestre="S5";
        }
        else if(params['cycle']==="Deuxième année du Cycle d'Ingénieur"){
          this.cycle="CI2";
          this.semestre="S7";
        }
        else if(params['cycle']==="Troisième année du Cycle d'Ingénieur"){
          this.cycle="CI3";
          this.semestre="S9";
        }
        this.filiere = params['filiere'];
        console.log(this.cycle);
        // console.log(this.filiere);

        this.fetchModules();
      }
    );
  }
}
