package ma.ac.ensa.gnotes.repository;

import ma.ac.ensa.gnotes.model.Enseignant;
import ma.ac.ensa.gnotes.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModuleRepo extends JpaRepository<Module,Long>{
    public Module findByNumero(String numero);
    public List<Module> findByEnseignant(Enseignant enseignant);
    @Query("select m from Module m where m.enseignant is null")
    public List<Module> findByEnseignant_NumeroNull();
    public List<Module> findByNomContains(String nom);
    public List<Module> findByCycleAndFiliere(String cycle,String filiere);
    public List<Module> findByCycle(String cycle);
}
