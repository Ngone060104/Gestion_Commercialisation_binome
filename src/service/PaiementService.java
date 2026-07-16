package service;

import entities.Facturation;
import entities.Paiement;
import repository.PaiementRepository;
import java.util.ArrayList;
import java.util.List;

public class PaiementService {
    private PaiementRepository PaiementRepository;
    private FacturationService facturationService;

    public PaiementService(PaiementRepository PaiementRepository, FacturationService facturationService) {
        this.PaiementRepository = PaiementRepository;
        this.facturationService = facturationService;
    }

    // EXIGENCE : Enregistrer un Paiement et mettre à jour le statut de la facture liée
    public boolean enregistrerPaiement(Paiement p) {
        Facturation facture = p.getFacture();
        if (facture == null) return false;

        // On enregistre le Paiement dans la facture et dans le dépôt
        facture.addPaiement(p);
        PaiementRepository.save(p);

        // On rafraîchit le statut de Paiement basé sur la logique métier
        p.setStatut(facturationService.calculerStatutFacture(facture));
        return true;
    }

    public List<Paiement> listerTousLesPaiements() {
        return PaiementRepository.findAll();
    }

    // EXIGENCE : Afficher les Paiements d'une facture spécifique
    public List<Paiement> listerPaiementsParFacture(int idFacture) {
        List<Paiement> resultat = new ArrayList<>();
        Facturation f = facturationService.rechercherParId(idFacture);
        if (f != null) {
            return f.getPaiements();
        }
        return resultat;
    }
}
