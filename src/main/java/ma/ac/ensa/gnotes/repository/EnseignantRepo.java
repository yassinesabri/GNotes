package ma.ac.ensa.gnotes.repository;

import ma.ac.ensa.gnotes.model.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnseignantRepo extends JpaRepository<Enseignant,Long>{
    public Enseignant findByNumeroAndPassword(String numero, String password);
    public Enseignant findByNumero(String numero);
    public List<Enseignant> findByNumeroContains(String numero);
}
