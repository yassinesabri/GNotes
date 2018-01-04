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

    public List<ModuleVO> findNotByEnseignant_Id(){
        List<Module> modules = moduleRepo.findByEnseignant_NumeroNull();
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
}
