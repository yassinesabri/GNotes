package ma.ac.ensa.gnotes.model;

import ma.ac.ensa.gnotes.utils.Privilege;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Etudiant {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "etudiant_id")
    private long id;

    @Column(unique = true)
    private String numero;

    private String nom;

    private String prenom;

    private String cin;

    private int cne;

    private String adresse;

    private String telephone;

    private String codePostale;

    private String filiere;

    private String cycle;

    private String email;

    @Enumerated(value = EnumType.STRING)
    private Privilege privilege;

    @Temporal(TemporalType.DATE)
    private Date dateDeNaissance;

    @OneToMany(mappedBy = "etudiant")
    private List<EtudiantModule> etudiantModuleList;

    public Etudiant() {
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

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public int getCne() {
        return cne;
    }

    public void setCne(int cne) {
        this.cne = cne;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getAdresse() { return adresse; }

    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getTelephone() { return telephone; }

    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getCodePostale() { return codePostale; }

    public void setCodePostale(String codePostale) { this.codePostale = codePostale; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public List<EtudiantModule> getEtudiantModuleList() {
        return etudiantModuleList;
    }

    public void setEtudiantModuleList(List<EtudiantModule> etudiantModuleList) {
        this.etudiantModuleList = etudiantModuleList;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
                ", numero=" + numero +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", cin='" + cin + '\'' +
                ", cne=" + cne +
                ", email=" + email +
                ", telephone =" + telephone +
                ", addresse=" + adresse +
                ", code postale=" + codePostale +
                ", privilege=" + privilege +
                ", dateDeNaissance=" + dateDeNaissance +
                '}';
    }
}
