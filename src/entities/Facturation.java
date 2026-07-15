package entities;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Facturation {
    private int id;
    private String numero;
    private Date date;
    private double montant;
    private Commande commande;
    private List<paiement> paiements;

    public Facturation() {
        this.paiements = new ArrayList<>();
    }

    public Facturation(int id, String numero, Date date, Commande commande) {
        this.id = id;
        this.numero = numero;
        this.date = date;
        this.commande = commande;
        this.montant = commande.getMontantTotal();
        this.paiements = new ArrayList<>();
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }

    public Commande getCommande() { return commande; }
    public void setCommande(Commande commande) { this.commande = commande; }

    public List<paiement> getPaiements() { return paiements; }
    public void addPaiement(paiement p) { this.paiements.add(p); }

    public void toChaine() {
        System.out.println("id=" + this.id + ", numero=" + this.numero + ", date=" + this.date + ", montant=" + this.montant + ", commande=" + (this.commande != null ? this.commande.getNumero() : "Aucune"));
    }

}
