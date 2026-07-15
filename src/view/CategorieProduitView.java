package view;

import entities.CategorieProduit;
import service.CategorieProduitService;
import java.util.Scanner;

public class CategorieProduitView {
    private CategorieProduitService service;
    private Scanner scanner;

    public CategorieProduitView(CategorieProduitService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void afficherMenu() {
        System.out.println("\n--- GESTION DES CATEGORIES ---");
        System.out.println("1. Ajouter une catégorie");
        System.out.println("2. Lister les catégories");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine(); // vider le tampon

        if (choix == 1) {
            String libelle = "";
            while (libelle.trim().isEmpty()) {
                System.out.print("Entrez le libellé de la catégorie (Obligatoire) : ");
                libelle = scanner.nextLine();
            }
            
            service.ajouterCategorie(new CategorieProduit(0, libelle));
            System.out.println("Catégorie ajoutée avec succès !");
        } else if (choix == 2) {
            System.out.println("\nListe des catégories :");
            for (CategorieProduit cat : service.listerCategories()) {
                cat.toChaine();
            }
        }
    }
}
