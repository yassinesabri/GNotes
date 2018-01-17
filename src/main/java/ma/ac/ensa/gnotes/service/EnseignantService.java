package ma.ac.ensa.gnotes.service;

import ma.ac.ensa.gnotes.model.Enseignant;
import ma.ac.ensa.gnotes.model.Module;
import ma.ac.ensa.gnotes.model.vo.EnseignantVO;
import ma.ac.ensa.gnotes.repository.EnseignantRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EnseignantService {
    @Autowired
    private EnseignantRepo enseignantRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Enseignant findByNumeroAndPassword(String numero, String password){
        Enseignant enseignant = enseignantRepo.findByNumeroAndPassword(numero, password);
        if(enseignant != null){
            enseignant.setModules(null);
        }
        return enseignant;
    }

    public Enseignant save(Enseignant enseignant){
        if(enseignant.getModules() != null){
            for(Module module:enseignant.getModules()){
                module.setEnseignant(enseignant);
            }
        }
        return enseignantRepo.save(enseignant);
    }

    public Enseignant findByNumero(String numero){
        return enseignantRepo.findByNumero(numero);
    }

    public EnseignantVO findByNumeroVO(String numero){
        Enseignant enseignant =  enseignantRepo.findByNumero(numero);
        EnseignantVO enseignantVO = modelMapper.map(enseignant, EnseignantVO.class);
        return enseignantVO;
    }

    public List<EnseignantVO> findAll(){
        ModelMapper modelMapper = new ModelMapper();
        List<Enseignant> enseignants = enseignantRepo.findAll();
        List<EnseignantVO> enseignantVOS = new ArrayList<>();
        for(Enseignant enseignant:enseignants){
            EnseignantVO enseignantVO = modelMapper.map(enseignant, EnseignantVO.class);
            enseignantVOS.add(enseignantVO);
            System.out.println(enseignantVO);
        }
        return enseignantVOS;
    }
    public List<EnseignantVO> findByNumeroContains(String numero){
        List<Enseignant> enseignants = enseignantRepo.findByNumeroContains(numero);
        List<EnseignantVO> enseignantVOS = new ArrayList<>();
        for(Enseignant enseignant:enseignants){
            EnseignantVO enseignantVO = modelMapper.map(enseignant, EnseignantVO.class);
            enseignantVOS.add(enseignantVO);
            System.out.println(enseignantVO);
        }
        return enseignantVOS;
    }

    public void deleteById(long id){
        enseignantRepo.deleteById(id);
    }
}
