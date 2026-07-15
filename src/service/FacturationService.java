package service;

import entities.Facturation;
import entities.paiement;
import enums.StatutPaiement;
import repository.FacturationRepository;
import java.util.ArrayList;
import java.util.List;

public class FacturationService {
    private FacturationRepository repository;

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

    // Calcule dynamiquement le statut actuel d'une facture en fonction de ses versements
    public StatutPaiement calculerStatutFacture(Facturation facture) {
        double totalVerse = 0;
        for (paiement p : facture.getPaiements()) {
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

    // BONUS : Afficher les factures impayées (non payées ou partiellement payées)
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

    // EXIGENCE : Afficher les factures soldées (totalement payées)
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
