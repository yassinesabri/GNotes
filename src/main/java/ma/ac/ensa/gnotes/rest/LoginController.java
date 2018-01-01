package ma.ac.ensa.gnotes.rest;

import antlr.collections.List;
import ma.ac.ensa.gnotes.model.Etudiant;
import ma.ac.ensa.gnotes.service.EnseignantService;
import ma.ac.ensa.gnotes.service.EtudiantService;
import ma.ac.ensa.gnotes.utils.LoginAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api",method = RequestMethod.POST)
public class LoginController {

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private EnseignantService enseignantService;

    @RequestMapping("login")
    Object authenticate(@RequestBody LoginAccount account){
        if(account.isStudent){
            return etudiantService.findByCneAndCin(Integer.parseInt(account.cne), account.cin);
        }else{
            return enseignantService.findByNumeroAndPassword(Long.parseLong(account.numero), account.password);
        }
    }
}
