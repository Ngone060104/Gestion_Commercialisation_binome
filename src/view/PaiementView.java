package view;

import entities.Facturation;
import entities.paiement;
import service.FacturationService;
import service.PaiementService;
import java.util.Date;
import java.util.Scanner;

public class PaiementView {
    private PaiementService service;
    private FacturationService facturationService;
    private Scanner scanner;

    public PaiementView(PaiementService service, FacturationService facturationService, Scanner scanner) {
        this.service = service;
        this.facturationService = facturationService;
        this.scanner = scanner;
    }

    public void afficherMenu() {
        System.out.println("\n--- GESTION DES PAIEMENTS ---");
        System.out.println("1. Enregistrer un paiement pour une facture");
        System.out.println("2. Afficher les paiements d'une facture");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        if (choix == 1) {
            System.out.println("\nFactures disponibles :");
            for (Facturation f : facturationService.listerFactures()) {
                f.toChaine();
                System.out.println("   Montant à payer : " + f.getMontant() + " | Statut : " + facturationService.calculerStatutFacture(f));
            }
            System.out.print("Entrez l'ID de la facture à payer : ");
            int idFact = scanner.nextInt();
            Facturation f = facturationService.rechercherParId(idFact);

            if (f == null) {
                System.out.println("Facture introuvable.");
                return;
            }

            System.out.print("Numéro de reçu de paiement : ");
            String numP = scanner.nextLine();
            System.out.print("Montant versé : ");
            double montant = scanner.nextDouble();

            paiement p = new paiement(0, numP, montant, new Date(), f);
            if (service.enregistrerPaiement(p)) {
                System.out.println("Paiement enregistré ! Nouveau statut calculé pour la facture.");
            } else {
                System.out.println("Erreur lors de la sauvegarde.");
            }

        } else if (choix == 2) {
            System.out.print("Entrez l'ID de la facture concernée : ");
            int idFact = scanner.nextInt();
            System.out.println("\nHistorique des règlements pour cette facture :");
            for (paiement p : service.listerPaiementsParFacture(idFact)) {
                p.toChaine();
            }
        }
    }
}
