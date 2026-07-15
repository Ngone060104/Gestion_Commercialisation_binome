package view;

import entities.Client;
import service.ClientService;
import java.util.Scanner;

public class ClientView {
    private ClientService service;
    private Scanner scanner;

    public ClientView(ClientService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void afficherMenu() {
        System.out.println("\n--- GESTION DES CLIENTS ---");
        System.out.println("1. Ajouter un client");
        System.out.println("2. Lister les clients");
        System.out.println("3. Rechercher un client par téléphone (Bonus)");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        if (choix == 1) {

    String nom = "";
    while (nom.trim().isEmpty()) {
        System.out.print("Nom (Obligatoire) : ");
        nom = scanner.nextLine();
    }

    String prenom = "";
    while (prenom.trim().isEmpty()) {
        System.out.print("Prénom (Obligatoire) : ");
        prenom = scanner.nextLine();
    }

    String tel = "";
    while (tel.trim().isEmpty()) {
        System.out.print("Téléphone (Obligatoire) : ");
        tel = scanner.nextLine();
    }

    service.ajouterClient(new Client(0, nom, prenom, tel));
    System.out.println("Client enregistré !");

        } else if (choix == 2) {
            System.out.println("\nListe des clients :");
            for (Client c : service.listerClients()) {
                c.toChaine();
            }
        } else if (choix == 3) {
            System.out.print("Entrez le numéro de téléphone à rechercher : ");
            String tel = scanner.nextLine();
            Client c = service.rechercherParTelephone(tel);
            if (c != null) {
                c.toChaine();
            } else {
                System.out.println("Aucun client trouvé avec ce numéro.");
            }
        }
    }
}
