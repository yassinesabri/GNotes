package ma.ac.ensa.gnotes.service;

import ma.ac.ensa.gnotes.model.Module;
import ma.ac.ensa.gnotes.repository.ModuleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ModuleService {

    @Autowired
    private ModuleRepo moduleRepo;

    public Module findByNumero(String numero){
        return moduleRepo.findByNumero(numero);
    }
}
