package ma.ac.ensa.gnotes.model.vo;

public class ModuleVO {
    private long id;

    private String numero;

    private String nom;

    private String filiere;

    private String cycle;

    private String semestre;

    public ModuleVO() {
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

    @Override
    public String toString() {
        return "ModuleVO{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", nom='" + nom + '\'' +
                ", filiere='" + filiere + '\'' +
                ", cycle='" + cycle + '\'' +
                ", semestre='" + semestre + '\'' +
                '}';
    }
}
