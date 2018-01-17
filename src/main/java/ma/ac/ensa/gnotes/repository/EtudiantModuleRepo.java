package ma.ac.ensa.gnotes.repository;

import ma.ac.ensa.gnotes.model.Etudiant;
import ma.ac.ensa.gnotes.model.EtudiantModule;
import ma.ac.ensa.gnotes.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtudiantModuleRepo extends JpaRepository<EtudiantModule,Long>{
    public EtudiantModule findByEtudiantAndModuleAndAnnee(Etudiant etudiant, Module module, String annee);
    public EtudiantModule findByEtudiantAndModule(Etudiant etudiant, Module module);
    public List<EtudiantModule> findByModuleAndAnnee(Module module, String annee);
}
