package ma.ac.ensa.gnotes.rest;


import ma.ac.ensa.gnotes.model.Etudiant;
import ma.ac.ensa.gnotes.model.EtudiantModule;
import ma.ac.ensa.gnotes.model.Module;
import ma.ac.ensa.gnotes.model.vo.EtudiantModuleVO;
import ma.ac.ensa.gnotes.model.vo.EtudiantVO;
import ma.ac.ensa.gnotes.model.vo.ModuleEtudiantVO;
import ma.ac.ensa.gnotes.model.vo.ModuleVO;
import ma.ac.ensa.gnotes.service.EtudiantModuleService;
import ma.ac.ensa.gnotes.service.EtudiantService;
import ma.ac.ensa.gnotes.service.ModuleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private EtudiantModuleService etudiantModuleService;

    @Autowired
    private ModelMapper modelMapper;

//    @RequestMapping(value = "/etudiant",  method = RequestMethod.GET, produces = "application/json")
//    public Etudiant getEtudiant(){
//        Etudiant etudiant=etudiantService.findByNumero("1500016");
//        etudiant.setEtudiantModuleList(null);
//        return etudiant;
//    }

    @RequestMapping(value = "fetchStudentModules/{cycle}/{filiere}/{etudiant_numero}", method = RequestMethod.GET)
    public EtudiantModuleVO fetchStudentModules(@PathVariable("cycle") String cycle, @PathVariable("filiere") String filiere,@PathVariable("etudiant_numero") String etudiant_numero){
        List<Module> listModules;
        List<ModuleVO> listModulesVO = new ArrayList<>();
        System.out.println(cycle);
        if(filiere.equals("none")) {
            listModules=this.moduleService.findByCycle(cycle);
//            System.out.println(listModules);
        }else{
            listModules=this.moduleService.findByCycleAndFiliere(cycle,filiere);
        }
        //eviter stackoverflow
        for(Module module:listModules){
            ModuleVO moduleVO = modelMapper.map(module, ModuleVO.class);
            listModulesVO.add(moduleVO);
        }
        Etudiant etudiant=this.etudiantService.findByNumero(etudiant_numero);
        List<EtudiantModule> etudiantModules=new ArrayList<EtudiantModule>();
        List<ModuleEtudiantVO> modulesEtudiantVO=new ArrayList<ModuleEtudiantVO>();
        for (int i=0;i<listModules.size();i++){
            ModuleEtudiantVO et=new ModuleEtudiantVO();
            listModules.get(i).setEtudiantModuleList(null);
            etudiantModules.add(this.etudiantModuleService.findByEtudiantAndModule(etudiant,listModules.get(i)));
//            System.out.println(etudiantModules.get(i));
            if(etudiantModules.get(i)!=null){
                et.setId(etudiantModules.get(i).getId());
                et.setNote(etudiantModules.get(i).getNote());
                et.setCommentaire(etudiantModules.get(i).getCommentaire());
                et.setNomModule(etudiantModules.get(i).getModule().getNom());
                et.setSemestre(etudiantModules.get(i).getModule().getSemestre());
            }
            System.out.println(et);
            modulesEtudiantVO.add(et);
        }
        EtudiantModuleVO etudiantModuleVO=new EtudiantModuleVO();
        etudiantModuleVO.setListeModule(listModulesVO);
        etudiantModuleVO.setListetudiantModule(modulesEtudiantVO);
        return etudiantModuleVO;

    }
}
