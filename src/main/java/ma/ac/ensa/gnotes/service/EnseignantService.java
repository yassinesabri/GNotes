package ma.ac.ensa.gnotes.service;

import ma.ac.ensa.gnotes.model.Enseignant;
import ma.ac.ensa.gnotes.repository.EnseignantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EnseignantService {
    @Autowired
    private EnseignantRepo enseignantRepo;

    public Enseignant findByNumeroAndPassword(long numero, String password){
        Enseignant enseignant = enseignantRepo.findByNumeroAndPassword(numero, password);
        if(enseignant != null){
            enseignant.setModules(null);
        }
        return enseignant;
    }
}
