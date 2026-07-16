package view;

import java.util.Scanner;

public class MenuView {
    private CategorieProduitView categorieView;
    private ClientView clientView;
    private ProduitView produitView;
    private CommandeView commandeView;
    private FacturationView facturationView;
    private PaiementView paiementView;
    private Scanner scanner;

    public MenuView(CategorieProduitView categorieView, ClientView clientView, ProduitView produitView,
                    CommandeView commandeView, FacturationView facturationView, PaiementView paiementView,
                    Scanner scanner) {
        this.categorieView = categorieView;
        this.clientView = clientView;
        this.produitView = produitView;
        this.commandeView = commandeView;
        this.facturationView = facturationView;
        this.paiementView = paiementView;
        this.scanner = scanner;
    }

    public void afficherMenuPrincipal() {
        int choix = 0;
        do {
            System.out.println("\n=============================================");
            System.out.println("   APPLICATION DE GESTION COMMERCIALE (M1)   ");
            System.out.println("=============================================");
            System.out.println("1. Gestion des Catégories de Produits");
            System.out.println("2. Gestion des Clients");
            System.out.println("3. Gestion des Produits");
            System.out.println("4. Gestion des Commandes");
            System.out.println("5. Gestion des Factures");
            System.out.println("6. Gestion des Paiements");
            System.out.println("7. Quitter l'application");
            System.out.println("=============================================");
            System.out.print("Veuillez saisir votre choix [1-7] : ");
            
            try {
                choix = scanner.nextInt();
                scanner.nextLine(); // Vider la ligne après la saisie d'un entier
                
                switch (choix) {
                    case 1:
                        categorieView.afficherMenu();
                        break;
                    case 2:
                        clientView.afficherMenu();
                        break;
                    case 3:
                        produitView.afficherMenu();
                        break;
                    case 4:
                        commandeView.afficherMenu();
                        break;
                    case 5:
                        facturationView.afficherMenu();
                        break;
                    case 6:
                        paiementView.afficherMenu();
                        break;
                    case 7:
                        System.out.println("\nMerci d'avoir utilisé l'application. Au revoir !");
                        break;
                    default:
                        System.out.println("Choix invalide ! Veuillez saisir un nombre entre 1 et 7.");
                }
            } catch (Exception e) {
                System.out.println("Erreur de saisie. Veuillez entrer un nombre valide.");
                scanner.nextLine(); // Nettoyer le scanner en cas d'erreur de format
                choix = 0;
            }
        } while (choix != 7);
    }
}
