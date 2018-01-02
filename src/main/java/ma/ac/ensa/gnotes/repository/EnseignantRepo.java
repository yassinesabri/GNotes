package ma.ac.ensa.gnotes.repository;

import ma.ac.ensa.gnotes.model.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnseignantRepo extends JpaRepository<Enseignant,Long>{
    public Enseignant findByNumeroAndPassword(String numero, String password);
}
