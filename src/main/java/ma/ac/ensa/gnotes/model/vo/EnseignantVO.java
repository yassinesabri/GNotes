package ma.ac.ensa.gnotes.model.vo;

import ma.ac.ensa.gnotes.utils.Privilege;

import java.util.List;

public class EnseignantVO {
    private long id;

    private String numero;

    private String nom;

    private String prenom;

    private String password;

    private Privilege privilege;

    private List<ModuleVO> modules;

    public EnseignantVO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public List<ModuleVO> getModules() {
        return modules;
    }

    public void setModules(List<ModuleVO> modules) {
        this.modules = modules;
    }

    @Override
    public String toString() {
        return "EnseignantVO{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", password='" + password + '\'' +
                ", privilege=" + privilege +
                ", modules=" + modules +
                '}';
    }
}
