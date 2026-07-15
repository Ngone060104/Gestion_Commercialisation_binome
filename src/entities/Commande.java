package entities;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Commande {
    private int id;
    private String numero;
    private Date date;
    private double montantTotal;
    private Client client;
    private List<ProduitCommande> produitCommandes;
    private Facturation facture;

    public Commande() {
        this.produitCommandes = new ArrayList<>();
        this.montantTotal = 0.0;
    }

    public Commande(int id, String numero, Date date, Client client) {
        this.id = id;
        this.numero = numero;
        this.date = date;
        this.client = client;
        this.produitCommandes = new ArrayList<>();
        this.montantTotal = 0.0;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public double getMontantTotal() { return montantTotal; }
    public void setMontantTotal(double montantTotal) { this.montantTotal = montantTotal; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public List<ProduitCommande> getProduitCommandes() { return produitCommandes; }
    
    public void addProduitCommande(ProduitCommande pc) {
        this.produitCommandes.add(pc);
        this.montantTotal += pc.getPrixS();
    }

    public Facturation getFacture() { return facture; }
    public void setFacture(Facturation facture) { this.facture = facture; }

    public void toChaine() {
        System.out.println("id=" + this.id + ", numero=" + this.numero + ", date=" + this.date + ", montantTotal=" + this.montantTotal + ", client=" + (this.client != null ? this.client.getNom() : "Aucun"));
    }

}
