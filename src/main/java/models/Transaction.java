package models;



import jakarta.persistence.*;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false)
    private Employees employer;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Products produit;

    @Column(name = "transaction_type", nullable = false)
    private String action;

    @Column(name = "texte", nullable = false)
    private String texte;

    @Column(name = "heure", nullable = false)
    private String heure;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "description", nullable = true)
    private String description;

    public String getTexte() {return texte;}
    public void setTexte(String texte) {this.texte = texte;}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Employees getEmployer() {
        return employer;
    }
    public void setEmployer(Employees employer) {
        this.employer = employer;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public String getHeure() {return heure;}
    public void setHeure(String heure) {this.heure = heure;}
    public Products getProduit() {return produit;}
    public void setProduit(Products produit) {this.produit = produit;}
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
