package ma.ac.ensa.gnotes.repository;

import ma.ac.ensa.gnotes.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EtudiantRepo extends JpaRepository<Etudiant,Long>{
    public Etudiant findByNumero(String numero);
    public Etudiant findByCneAndCin(int cne, String cin);
    public Etudiant findByNumeroOrCneOrCin(String numero,int cne, String cin);
    public Etudiant save(Etudiant etudiant);
    public List<Etudiant> findAll();
    public List<Etudiant> findByNumeroContains(String numero);
    public Etudiant findById(long id);
}
