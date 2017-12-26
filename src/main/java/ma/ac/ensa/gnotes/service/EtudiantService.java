package ma.ac.ensa.gnotes.service;

import ma.ac.ensa.gnotes.model.Etudiant;
import ma.ac.ensa.gnotes.repository.EtudiantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EtudiantService {

    @Autowired
    private EtudiantRepo etudiantRepo;

    public Etudiant findByNumero(long numero){
        return etudiantRepo.findByNumero(numero);
    }
}
