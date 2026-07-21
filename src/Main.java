import service.*;
import view.*;
import java.util.Scanner;

public class Main {
public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Le Main crée UNIQUEMENT les SERVICES
        // Les services créent eux-mêmes leurs repositories (ou les reçoivent)
        ClientService clientService = new ClientService();
        CategorieProduitService categorieService = new CategorieProduitService();
        ProduitService produitService = new ProduitService();
        CommandeService commandeService = new CommandeService();
        FacturationService facturationService = new FacturationService();
        PaiementService paiementService = new PaiementService();

        //Les Views reçoivent les SERVICES (pas les repositories)
        ClientView clientView = new ClientView(clientService, scanner);
        CategorieProduitView categorieView = new CategorieProduitView(categorieService, scanner);
        ProduitView produitView = new ProduitView(produitService, categorieService, scanner);
        CommandeView commandeView = new CommandeView(commandeService, clientService, produitService, scanner);
        FacturationView facturationView = new FacturationView(facturationService, scanner);
        PaiementView paiementView = new PaiementView(paiementService, facturationService, scanner);

        //  Menu
        MenuView menuPrincipal = new MenuView(
            categorieView, clientView, produitView, 
            commandeView, facturationView, paiementView, 
            scanner
        );

        menuPrincipal.afficherMenuPrincipal();
        scanner.close();
    }
}