package ma.ac.ensa.gnotes.repository;

import ma.ac.ensa.gnotes.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepo extends JpaRepository<Module,Long>{
    public Module findByNumero(String numero);
}
