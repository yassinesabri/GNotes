package ma.ac.ensa.gnotes.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "module_id")
    private long id;

    @Column(unique = true)
    private String numero;

    private String nom;

    private String filiere;

    private String cycle;

    private String semestre;

    @ManyToOne
    private Enseignant enseignant;

    @OneToMany(mappedBy = "module")
    private List<EtudiantModule> etudiantModuleList;

    public Module() {
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

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public List<EtudiantModule> getEtudiantModuleList() {
        return etudiantModuleList;
    }

    public void setEtudiantModuleList(List<EtudiantModule> etudiantModuleList) {
        this.etudiantModuleList = etudiantModuleList;
    }

    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", nom='" + nom + '\'' +
                ", filiere='" + filiere + '\'' +
                ", cycle='" + cycle + '\'' +
                ", semestre='" + semestre + '\'' +
                ", enseignant=" + enseignant +
                '}';
    }
}
