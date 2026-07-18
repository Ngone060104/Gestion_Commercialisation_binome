package service;

import entities.Facturation;
import entities.Paiement;
import repository.PaiementRepository;
import java.util.ArrayList;
import java.util.List;

public class PaiementService {
    private PaiementRepository paiementRepository;
    private FacturationService facturationService;

    // Constructeur sans paramètre
    public PaiementService() {
        this.paiementRepository = new PaiementRepository();
        this.facturationService = new FacturationService();
    }

    //  Pour injection (si besoin)
    public PaiementService(PaiementRepository paiementRepository, FacturationService facturationService) {
        this.paiementRepository = paiementRepository;
        this.facturationService = facturationService;
    }

    public boolean enregistrerPaiement(Paiement p) {
        Facturation facture = p.getFacture();
        if (facture == null) return false;

        facture.addPaiement(p);
        paiementRepository.save(p);
        p.setStatut(facturationService.calculerStatutFacture(facture));
        return true;
    }

    public List<Paiement> listerTousLesPaiements() {
        return paiementRepository.findAll();
    }

    public List<Paiement> listerPaiementsParFacture(int idFacture) {
        List<Paiement> resultat = new ArrayList<>();
        Facturation f = facturationService.rechercherParId(idFacture);
        if (f != null) {
            return f.getPaiements();
        }
        return resultat;
    }
}