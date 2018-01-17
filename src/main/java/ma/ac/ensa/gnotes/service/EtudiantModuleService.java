package ma.ac.ensa.gnotes.service;

import ma.ac.ensa.gnotes.model.Etudiant;
import ma.ac.ensa.gnotes.model.EtudiantModule;
import ma.ac.ensa.gnotes.model.Module;
import ma.ac.ensa.gnotes.repository.EtudiantModuleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EtudiantModuleService {

    @Autowired
    private EtudiantModuleRepo etudiantModuleRepo;

    public EtudiantModule save(EtudiantModule etudiantModule){
        return etudiantModuleRepo.save(etudiantModule);
    }

    public EtudiantModule findByEtudiantAndModuleAndAnnee(Etudiant etudiant, Module module, String annee){
        return etudiantModuleRepo.findByEtudiantAndModuleAndAnnee(etudiant, module, annee);
    }

    public EtudiantModule findByEtudiantAndModule(Etudiant etudiant, Module module){
        return this.etudiantModuleRepo.findByEtudiantAndModule(etudiant, module);
    }
    public List<EtudiantModule> findByModuleAndAnnee(Module module, String annee){
        return this.etudiantModuleRepo.findByModuleAndAnnee(module,annee);
    }
}
