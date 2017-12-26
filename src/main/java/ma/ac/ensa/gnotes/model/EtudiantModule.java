package ma.ac.ensa.gnotes.model;

import javax.persistence.*;

@Entity
@Table(name = "etudiant_module")
public class EtudiantModule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "etudiant_module_id")
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "module_id")
    private Module module;

    private double note;

    private String commentaire;

    public EtudiantModule() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
