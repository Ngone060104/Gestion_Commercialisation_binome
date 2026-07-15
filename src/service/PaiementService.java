package service;

import entities.Facturation;
import entities.paiement;
import repository.PaiementRepository;
import java.util.ArrayList;
import java.util.List;

public class PaiementService {
    private PaiementRepository paiementRepository;
    private FacturationService facturationService;

    public PaiementService(PaiementRepository paiementRepository, FacturationService facturationService) {
        this.paiementRepository = paiementRepository;
        this.facturationService = facturationService;
    }

    // EXIGENCE : Enregistrer un paiement et mettre à jour le statut de la facture liée
    public boolean enregistrerPaiement(paiement p) {
        Facturation facture = p.getFacture();
        if (facture == null) return false;

        // On enregistre le paiement dans la facture et dans le dépôt
        facture.addPaiement(p);
        paiementRepository.save(p);

        // On rafraîchit le statut de paiement basé sur la logique métier
        p.setStatut(facturationService.calculerStatutFacture(facture));
        return true;
    }

    public List<paiement> listerTousLesPaiements() {
        return paiementRepository.findAll();
    }

    // EXIGENCE : Afficher les paiements d'une facture spécifique
    public List<paiement> listerPaiementsParFacture(int idFacture) {
        List<paiement> resultat = new ArrayList<>();
        Facturation f = facturationService.rechercherParId(idFacture);
        if (f != null) {
            return f.getPaiements();
        }
        return resultat;
    }
}
