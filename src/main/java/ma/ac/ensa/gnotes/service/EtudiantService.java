package ma.ac.ensa.gnotes.service;

import ma.ac.ensa.gnotes.model.Etudiant;
import ma.ac.ensa.gnotes.repository.EtudiantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EtudiantService {

    @Autowired
    private EtudiantRepo etudiantRepo;

    public Etudiant findByNumero(String numero){
        return etudiantRepo.findByNumero(numero);
    }

    public Etudiant findByCneAndCin(int cne, String cin){
        Etudiant etudiant = etudiantRepo.findByCneAndCin(cne, cin);
        if(etudiant != null){
            etudiant.setEtudiantModuleList(null);
        }
        return etudiant;
    }
    public Etudiant findByNumeroOrCneOrCin(String numero,int cne, String cin){
        return etudiantRepo.findByNumeroOrCneOrCin(numero, cne, cin);
    }

    public Etudiant save(Etudiant etudiant){
        return etudiantRepo.save(etudiant);
    }

    public List<Etudiant> findAll(){
        List<Etudiant> etudiants = etudiantRepo.findAll();
        for(Etudiant etudiant : etudiants){
            etudiant.setEtudiantModuleList(null);
        }
        return etudiants;
    }
    public List<Etudiant> findByNumeroContains(String numero){
        List<Etudiant> etudiants = etudiantRepo.findByNumeroContains(numero);
        for(Etudiant etudiant : etudiants){
            etudiant.setEtudiantModuleList(null);
        }
        return etudiants;
    }
}
