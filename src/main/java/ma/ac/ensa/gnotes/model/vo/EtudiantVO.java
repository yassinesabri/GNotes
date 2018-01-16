package ma.ac.ensa.gnotes.model.vo;

import ma.ac.ensa.gnotes.utils.Privilege;

public class EtudiantVO {
    private String numero;

    private String nom;

    private String prenom;

    private String cin;

    private int cne;

    private Privilege privilege;

    private String dateDeNaissance;

    private String adresse;

    private String telephone;

    private String codePostale;

    private String email;

    public EtudiantVO(String numero, String nom, String prenom, String cin, int cne, Privilege privilege, String dateDeNaissance, String adresse, String telephone, String codePostale, String email) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.cne = cne;
        this.privilege = privilege;
        this.dateDeNaissance = dateDeNaissance;
        this.adresse = adresse;
        this.telephone = telephone;
        this.codePostale = codePostale;
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCodePostale() {
        return codePostale;
    }

    public void setCodePostale(String codePostale) {
        this.codePostale = codePostale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EtudiantVO() {
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
        return "EtudiantVO{" +
                "numero='" + numero + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", cin='" + cin + '\'' +
                ", cne=" + cne +
                ", privilege=" + privilege +
                ", dateDeNaissance='" + dateDeNaissance + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", codePostale='" + codePostale + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
