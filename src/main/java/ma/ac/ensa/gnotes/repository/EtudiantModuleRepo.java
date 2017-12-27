package ma.ac.ensa.gnotes.repository;

import ma.ac.ensa.gnotes.model.Etudiant;
import ma.ac.ensa.gnotes.model.EtudiantModule;
import ma.ac.ensa.gnotes.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantModuleRepo extends JpaRepository<EtudiantModule,Long>{
    public EtudiantModule findByEtudiantAndModuleAndAnnee(Etudiant etudiant, Module module, String annee);
}
