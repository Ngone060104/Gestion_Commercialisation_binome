import repository.*;
import service.*;
import view.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. Initialisation du Scanner partagé pour toute l'application
        Scanner scanner = new Scanner(System.in);

        // 2. Instanciation de la couche REPOSITORY
        CategorieProduitRepository categorieRepo = new CategorieProduitRepository();
        ClientRepository clientRepo = new ClientRepository();
        ProduitRepository produitRepo = new ProduitRepository();
        CommandeRepository commandeRepo = new CommandeRepository();
        FacturationRepository facturationRepo = new FacturationRepository();
        PaiementRepository paiementRepo = new PaiementRepository();

        // 3. Instanciation de la couche SERVICE (avec injection des repositories)
        CategorieProduitService categorieService = new CategorieProduitService(categorieRepo);
        ClientService clientService = new ClientService(clientRepo);
        ProduitService produitService = new ProduitService(produitRepo);
        FacturationService facturationService = new FacturationService(facturationRepo);
        
        // CommandeService a besoin de FacturationService pour la génération automatique
        CommandeService commandeService = new CommandeService(commandeRepo, facturationService);
        // PaiementService a besoin de FacturationService pour mettre à jour les statuts
        PaiementService paiementService = new PaiementService(paiementRepo, facturationService);

        // 4. Instanciation de la couche VIEW (avec injection des services et du scanner)
        CategorieProduitView categorieView = new CategorieProduitView(categorieService, scanner);
        ClientView clientView = new ClientView(clientService, scanner);
        ProduitView produitView = new ProduitView(produitService, categorieService, scanner);
        CommandeView commandeView = new CommandeView(commandeService, clientService, produitService, scanner);
        FacturationView facturationView = new FacturationView(facturationService, scanner);
        PaiementView paiementView = new PaiementView(paiementService, facturationService, scanner);

        // 5. Instanciation du Menu Principal et lancement de l'application
        MenuView menuPrincipal = new MenuView(
            categorieView, 
            clientView, 
            produitView, 
            commandeView, 
            facturationView, 
            paiementView, 
            scanner
        );

        // Lancement de la boucle infinie du menu console
        menuPrincipal.afficherMenuPrincipal();

        // Fermeture propre du scanner à la fermeture de l'application
        scanner.close();
    }
}
