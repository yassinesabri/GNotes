package ma.ac.ensa.gnotes.rest;


import ma.ac.ensa.gnotes.model.Etudiant;
import ma.ac.ensa.gnotes.model.EtudiantModule;
import ma.ac.ensa.gnotes.service.EtudiantModuleService;
import ma.ac.ensa.gnotes.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private EtudiantModuleService etudiantModuleService;

    @RequestMapping(value = "/etudiant",  method = RequestMethod.GET, produces = "application/json")
    public Etudiant getEtudiant(){
        Etudiant etudiant=etudiantService.findByNumero((long)1500016);
        etudiant.setEtudiantModuleList(null);
//        Etudiant etudiantToSend=new Etudiant();
//        etudiantToSend.setId(etudiant.getId());
//        etudiantToSend.setNumero(etudiant.getNumero());
//        etudiantToSend.setNom(etudiant.getNom());
//        etudiantToSend.setPrenom(etudiant.getPrenom());
//        etudiantToSend.setCin(etudiant.getCin());
//        etudiantToSend.setCne(etudiant.getCne());
//        etudiantToSend.setPrivilege(etudiant.getPrivilege());
//        etudiantToSend.setDateDeNaissance(etudiant.getDateDeNaissance());

        return etudiant;
    }
}
