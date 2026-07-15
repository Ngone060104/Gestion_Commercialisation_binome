package entities;

import enums.StatutProduit;

public class Produit {
    private int id;
    private String libelle;
    private int qteStock;
    private double prixUnitaire;
    private StatutProduit statut;
    private CategorieProduit categorie;

    public Produit() {
        this.statut = StatutProduit.disponible;
    }

    public Produit(int id, String libelle, int qteStock, double prixUnitaire, CategorieProduit categorie) {
        this.id = id;
        this.libelle = libelle;
        this.qteStock = qteStock;
        this.prixUnitaire = prixUnitaire;
        this.categorie = categorie;
        this.statut = qteStock > 0 ? StatutProduit.disponible : StatutProduit.rupture;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public int getQteStock() { return qteStock; }
    public void setQteStock(int qteStock) { 
        this.qteStock = qteStock;
        this.statut = qteStock > 0 ? StatutProduit.disponible : StatutProduit.rupture;
    }

    public double getPrixUnitaire() { return prixUnitaire; }
    public void setPrixUnitaire(double prixUnitaire) { this.prixUnitaire = prixUnitaire; }

    public StatutProduit getStatut() { return statut; }
    public void setStatut(StatutProduit statut) { this.statut = statut; }

    public CategorieProduit getCategorie() { return categorie; }
    public void setCategorie(CategorieProduit categorie) { this.categorie = categorie; }
    
        public void toChaine() {
        System.out.println("id=" + this.id + ", libelle=" + this.libelle + ", qteStock=" + this.qteStock + ", prixUnitaire=" + this.prixUnitaire + ", statut=" + this.statut + ", categorie=" + (this.categorie != null ? this.categorie.getLibelle() : "Aucune"));
    }

    
}
