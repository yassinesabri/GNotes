package ma.ac.ensa.gnotes.service;

import ma.ac.ensa.gnotes.model.EtudiantModule;
import ma.ac.ensa.gnotes.repository.EtudiantModuleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EtudiantModuleService {

    @Autowired
    private EtudiantModuleRepo etudiantModuleRepo;

    public EtudiantModule save(EtudiantModule etudiantModule){
        return etudiantModuleRepo.save(etudiantModule);
    }
}
