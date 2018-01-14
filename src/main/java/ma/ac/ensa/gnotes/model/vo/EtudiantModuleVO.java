package ma.ac.ensa.gnotes.model.vo;

import ma.ac.ensa.gnotes.model.EtudiantModule;
import ma.ac.ensa.gnotes.model.Module;

import java.util.List;

public class EtudiantModuleVO {

    private List<Module> ListeModule;
    private List<ModuleEtudiantVO> ListetudiantModule;

    public List<Module> getListeModule() {
        return ListeModule;
    }

    public void setListeModule(List<Module> listeModule) {
        ListeModule = listeModule;
    }

    public List<ModuleEtudiantVO> getListetudiantModule() {
        return ListetudiantModule;
    }

    public void setListetudiantModule(List<ModuleEtudiantVO> listetudiantModule) {
        ListetudiantModule = listetudiantModule;
    }
}
