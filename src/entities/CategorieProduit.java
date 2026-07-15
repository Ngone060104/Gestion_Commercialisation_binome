package entities;

import java.util.ArrayList;
import java.util.List;

public class CategorieProduit {
    private int id;
    private String libelle;
    private List<Produit> produits;

    public CategorieProduit() {
        this.produits = new ArrayList<>();
    }

    public CategorieProduit(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
        this.produits = new ArrayList<>();
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public List<Produit> getProduits() { return produits; }
    public void addProduit(Produit produit) { this.produits.add(produit); }

        public void toChaine() {
        System.out.println("id=" + this.id + ", libelle=" + this.libelle);
    }

}
