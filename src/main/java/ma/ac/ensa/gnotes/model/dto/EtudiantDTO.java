package ma.ac.ensa.gnotes.model.dto;

import ma.ac.ensa.gnotes.utils.Privilege;

public class EtudiantDTO {
    private String numero;

    private String nom;

    private String prenom;

    private String cin;

    private int cne;

    private Privilege privilege;

    private String dateDeNaissance;

    public EtudiantDTO(String numero, String nom, String prenom, String cin, int cne, Privilege privilege, String dateDeNaissance) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.cne = cne;
        this.privilege = privilege;
        this.dateDeNaissance = dateDeNaissance;
    }

    public EtudiantDTO() {
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

    public String getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(String dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    @Override
    public String toString() {
        return "EtudiantDTO{" +
                "numero=" + numero +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", cin='" + cin + '\'' +
                ", cne=" + cne +
                ", privilege=" + privilege +
                ", dateDeNaissance='" + dateDeNaissance + '\'' +
                '}';
    }
}
