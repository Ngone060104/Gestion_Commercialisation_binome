package service;

import entities.Facturation;
import entities.Paiement;
import enums.StatutPaiement;
import repository.FacturationRepository;
import java.util.ArrayList;
import java.util.List;

public class FacturationService {
    private FacturationRepository repository;

    // Constructeur sans paramètre
    public FacturationService() {
        this.repository = new FacturationRepository();
    }

    // Pour injection (si besoin)
    public FacturationService(FacturationRepository repository) {
        this.repository = repository;
    }

    public void ajouterFacture(Facturation facture) {
        repository.save(facture);
    }

    public List<Facturation> listerFactures() {
        return repository.findAll();
    }

    public Facturation rechercherParId(int id) {
        return repository.findById(id);
    }

    public StatutPaiement calculerStatutFacture(Facturation facture) {
        double totalVerse = 0;
        for (Paiement p : facture.getPaiements()) {
            totalVerse += p.getMontantVerse();
        }

        if (totalVerse <= 0) {
            return StatutPaiement.non_payee;
        } else if (totalVerse >= facture.getMontant()) {
            return StatutPaiement.totalement_payee;
        } else {
            return StatutPaiement.partiellement_payee;
        }
    }

    public List<Facturation> listerFacturesImpayees() {
        List<Facturation> impayees = new ArrayList<>();
        for (Facturation f : repository.findAll()) {
            StatutPaiement statut = calculerStatutFacture(f);
            if (statut == StatutPaiement.non_payee || statut == StatutPaiement.partiellement_payee) {
                impayees.add(f);
            }
        }
        return impayees;
    }

    public List<Facturation> listerFacturesSoldees() {
        List<Facturation> soldees = new ArrayList<>();
        for (Facturation f : repository.findAll()) {
            if (calculerStatutFacture(f) == StatutPaiement.totalement_payee) {
                soldees.add(f);
            }
        }
        return soldees;
    }
}