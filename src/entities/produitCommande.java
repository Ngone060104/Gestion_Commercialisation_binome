package entities;

public class ProduitCommande {
    private int id;
    private int quantite;
    private double prixS; // Prix de saisie au moment de la commande
    private Produit produit;
    private Commande commande;

    public ProduitCommande() {}

    public ProduitCommande(int id, int quantite, Produit produit, Commande commande) {
        this.id = id;
        this.quantite = quantite;
        this.produit = produit;
        this.commande = commande;
        this.prixS = produit.getPrixUnitaire() * quantite;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public double getPrixS() { return prixS; }
    public void setPrixS(double prixS) { this.prixS = prixS; }

    public Produit getProduit() { return produit; }
    public void setProduit(Produit produit) { this.produit = produit; }

    public Commande getCommande() { return commande; }
    public void setCommande(Commande commande) { this.commande = commande; }

        public void toChaine() {
        System.out.println("id=" + this.id + ", quantite=" + this.quantite + ", prixS=" + this.prixS + ", produit=" + (this.produit != null ? this.produit.getLibelle() : "Aucun"));
        //    System.out.println("id=" + this.id + ", quantite=" + this.quantite + ", prixS=" + this.prixS + ", produit=" + (this.produit != null ? this.produit.getLibelle() : "Aucun"));
        

    }

}
