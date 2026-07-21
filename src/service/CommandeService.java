package service;

import entities.Commande;
import entities.Facturation;
import repository.CommandeRepository;
// import repository.FacturationRepository;
import java.util.Date;
import java.util.List;

public class CommandeService {
    private CommandeRepository commandeRepository;
    private FacturationService facturationService;

    // Constructeur sans paramètre
    public CommandeService() {
        this.commandeRepository = new CommandeRepository();
        this.facturationService = new FacturationService();
    }

    // Pour injection (si besoin)
    public CommandeService(CommandeRepository commandeRepository, FacturationService facturationService) {
        this.commandeRepository = commandeRepository;
        this.facturationService = facturationService;
    }

    public void enregistrerCommande(Commande commande) {
        commandeRepository.save(commande);
        
        String numFacture = "FAC-" + commande.getNumero().substring(4);
        Facturation facture = new Facturation(0, numFacture, new Date(), commande);
        
        commande.setFacture(facture);
        facturationService.ajouterFacture(facture);
    }

    public List<Commande> listerCommandes() {
        return commandeRepository.findAll();
    }
}