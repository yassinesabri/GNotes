package ma.ac.ensa.gnotes.service;

import ma.ac.ensa.gnotes.model.Enseignant;
import ma.ac.ensa.gnotes.model.Module;
import ma.ac.ensa.gnotes.model.vo.ModuleVO;
import ma.ac.ensa.gnotes.repository.ModuleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ModuleService {

    @Autowired
    private ModuleRepo moduleRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Module findByNumero(String numero){
        return moduleRepo.findByNumero(numero);
    }

    public ModuleVO findByNumeroVO(String numero){
        Module module = moduleRepo.findByNumero(numero);
        if(module == null){
            return null;
        }else{
            ModuleVO moduleVO = modelMapper.map(module, ModuleVO.class);
            return moduleVO;
        }
    }

    public List<ModuleVO> findNotByEnseignant_Id(){
        List<Module> modules = moduleRepo.findByEnseignant_NumeroNull();
        List<ModuleVO> moduleVOS = new ArrayList<>();
        for(Module module:modules){
            ModuleVO moduleVO = modelMapper.map(module, ModuleVO.class);
            moduleVOS.add(moduleVO);
        }
        return moduleVOS;
    }
    public List<ModuleVO> findAllVO(){
        List<Module> modules = moduleRepo.findAll();
        List<ModuleVO> moduleVOS = new ArrayList<>();
        for(Module module:modules){
            ModuleVO moduleVO = modelMapper.map(module, ModuleVO.class);
            moduleVOS.add(moduleVO);
        }
        return moduleVOS;
    }
    public List<ModuleVO> findByName(String name){
        List<Module> modules = moduleRepo.findByNomContains(name);
        List<ModuleVO> moduleVOS = new ArrayList<>();
        for(Module module:modules){
            ModuleVO moduleVO = modelMapper.map(module, ModuleVO.class);
            moduleVOS.add(moduleVO);
        }
        return moduleVOS;
    }
    public Module save(Module module){
        return moduleRepo.save(module);
    }

    public List<Module> findByCycleAndFiliere(String cycle,String filiere){
        return moduleRepo.findByCycleAndFiliere(cycle,filiere);
    }
    public List<Module> findByCycle(String cycle){
        return moduleRepo.findByCycle(cycle);
    }

    public List<ModuleVO> findByEnseignant(Enseignant enseignant){
        List<Module> modules = moduleRepo.findByEnseignant(enseignant);
        List<ModuleVO> moduleVOS = new ArrayList<>();
        for(Module module:modules){
            ModuleVO moduleVO = modelMapper.map(module, ModuleVO.class);
            moduleVOS.add(moduleVO);
        }
        return moduleVOS;
    }

    public void deleteById(long id)
    {
        moduleRepo.deleteById(id);
    }
}
