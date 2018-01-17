package ma.ac.ensa.gnotes.model.vo;

import ma.ac.ensa.gnotes.model.EtudiantModule;
import ma.ac.ensa.gnotes.model.Module;

import java.util.List;

public class EtudiantModuleVO {

    private List<ModuleVO> ListeModule;
    private List<ModuleEtudiantVO> ListetudiantModule;

    public List<ModuleVO> getListeModule() {
        return ListeModule;
    }

    public void setListeModule(List<ModuleVO> listeModule) {
        ListeModule = listeModule;
    }

    public List<ModuleEtudiantVO> getListetudiantModule() {
        return ListetudiantModule;
    }

    public void setListetudiantModule(List<ModuleEtudiantVO> listetudiantModule) {
        ListetudiantModule = listetudiantModule;
    }
}
