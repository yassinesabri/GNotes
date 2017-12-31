package ma.ac.ensa.gnotes.repository;

import ma.ac.ensa.gnotes.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepo extends JpaRepository<Etudiant,Long>{
    public Etudiant findByNumero(long numero);
    public Etudiant findByCneAndCin(int cne, String cin);
}
