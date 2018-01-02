package ma.ac.ensa.gnotes.service;

import ma.ac.ensa.gnotes.model.Enseignant;
import ma.ac.ensa.gnotes.model.Module;
import ma.ac.ensa.gnotes.repository.EnseignantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EnseignantService {
    @Autowired
    private EnseignantRepo enseignantRepo;

    public Enseignant findByNumeroAndPassword(String numero, String password){
        Enseignant enseignant = enseignantRepo.findByNumeroAndPassword(numero, password);
        if(enseignant != null){
            enseignant.setModules(null);
        }
        return enseignant;
    }

    public Enseignant save(Enseignant enseignant){
        return enseignantRepo.save(enseignant);
    }

    public Enseignant findByNumero(String numero){
        return enseignantRepo.findByNumero(numero);
    }
    public List<Enseignant> findAll(){
        List<Enseignant> enseignants = enseignantRepo.findAll();
        for(Enseignant enseignant:enseignants){
            for(Module module: enseignant.getModules()){
                module.setEtudiantModuleList(null);
            }
        }
        return enseignants;
    }
    public List<Enseignant> findByNumeroContains(String numero){
        List<Enseignant> enseignants = enseignantRepo.findByNumeroContains(numero);
        for(Enseignant enseignant:enseignants){
            for(Module module: enseignant.getModules()){
                module.setEtudiantModuleList(null);
            }
        }
        return enseignants;
    }

    public void deleteById(long id){
        enseignantRepo.deleteById(id);
    }
}
