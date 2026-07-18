package view;

import entities.Facturation;
import service.FacturationService;
import java.util.Scanner;

public class FacturationView {
    private FacturationService service;
    private Scanner scanner;

    public FacturationView(FacturationService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void afficherMenu() {
        System.out.println("\n--- GESTION DES FACTURES ---");
        System.out.println("1. Afficher toutes les factures");
        System.out.println("2. Afficher les factures impayées");
        System.out.println("3. Afficher les factures soldées");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        if (choix == 1) {
            System.out.println("\nToutes les factures :");
            for (Facturation f : service.listerFactures()) {
                f.toChaine();
                System.out.println("   Statut de paiement actuel : " + service.calculerStatutFacture(f));
            }
        } else if (choix == 2) {
            System.out.println("\nFactures impayées ou partiellement payées :");
            for (Facturation f : service.listerFacturesImpayees()) {
                f.toChaine();
                System.out.println("   Statut actuel : " + service.calculerStatutFacture(f));
            }
        } else if (choix == 3) {
            System.out.println("\nFactures soldées :");
            for (Facturation f : service.listerFacturesSoldees()) {
                f.toChaine();
                System.out.println("   Commande liée : " + f.getCommande().getNumero());
            }
        }
    }
}
