package ma.ac.ensa.gnotes.model;

import ma.ac.ensa.gnotes.utils.Privilege;

import javax.persistence.*;
import java.util.List;

@Entity
public class Enseignant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private long numero;

    private String nom;

    private String prenom;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Privilege privilege;

    @OneToMany(mappedBy = "enseignant")
    private List<Module> modules;

    public Enseignant() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
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

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
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

    @Override
    public String toString() {
        return "Enseignant{" +
                "id=" + id +
                ", numero=" + numero +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", modules=" + modules +
                ", privilege=" + privilege +
                '}';
    }
}
