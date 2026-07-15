package view;

import entities.*;
import service.*;
import java.util.Date;
import java.util.Scanner;

public class CommandeView {
    private CommandeService service;
    private ClientService clientService;
    private ProduitService produitService;
    private Scanner scanner;

    public CommandeView(CommandeService service, ClientService clientService, ProduitService produitService, Scanner scanner) {
        this.service = service;
        this.clientService = clientService;
        this.produitService = produitService;
        this.scanner = scanner;
    }

    public void afficherMenu() {
        System.out.println("\n--- GESTION DES COMMANDES ---");
        System.out.println("1. Créer une commande (avec au moins 1 produit)");
        System.out.println("2. Afficher toutes les commandes");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        if (choix == 1) {
            System.out.print("Entrez le numéro de téléphone du client : ");
            String tel = scanner.nextLine();
            Client client = clientService.rechercherParTelephone(tel);
            if (client == null) {
                System.out.println("Client introuvable. Veuillez d'abord le créer !");
                return;
            }

            
            System.out.print("Numéro de commande (ex: CMD001) : ");
            String numero = scanner.nextLine();

            Commande commande = new Commande(0, numero, new Date(), client);
            client.addCommande(commande);

            boolean continuer = true;
            int pcIdCounter = 1;

            while (continuer) {
                System.out.println("\nProduits disponibles :");
                for (Produit p : produitService.listerProduits()) {
                    p.toChaine();
                }
                System.out.print("Sélectionnez l'ID du produit à ajouter : ");
                int idProd = scanner.nextInt();
                Produit prod = produitService.rechercherParId(idProd);

                if (prod == null || prod.getQteStock() <= 0) {
                    System.out.println("Produit introuvable ou en rupture de stock !");
                    continue;
                }

                System.out.print("Quantité désirée (Stock dispo: " + prod.getQteStock() + ") : ");
                int qte = scanner.nextInt();

                while (qte <= 0 || qte > prod.getQteStock()) {
                    System.out.println("Quantité incorrecte ou insuffisante en stock.");
                    System.out.print("Entrez une quantité valide (Entre 1 et " + prod.getQteStock() + ") : ");
                    qte = scanner.nextInt();
                }

                // Décrémentation automatique du stock
                produitService.modifierQuantiteStock(prod.getId(), prod.getQteStock() - qte);

                // Ajout de la classe associative
                ProduitCommande pc = new ProduitCommande(pcIdCounter++, qte, prod, commande);
                commande.addProduitCommande(pc);

                System.out.print("Ajouter un autre produit ? (1: Oui / 2: Non) : ");
                int rep = scanner.nextInt();
                if (rep != 1) continuer = false;
            }

            if (!commande.getProduitCommandes().isEmpty()) {
                service.enregistrerCommande(commande);
                System.out.println("Commande validée et enregistrée ! Facture générée automatiquement.");
            } else {
                System.out.println("Commande vide annulée.");
            }

        } else if (choix == 2) {
            System.out.println("\nListe de toutes les commandes :");
            for (Commande cmd : service.listerCommandes()) {
                cmd.toChaine();
                System.out.println("  -> Contenu :");
                for (ProduitCommande pc : cmd.getProduitCommandes()) {
                    System.out.print("     ");
                    pc.toChaine();
                }
            }
        }
    }
}
