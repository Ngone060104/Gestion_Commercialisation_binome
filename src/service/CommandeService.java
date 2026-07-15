package service;

import entities.Commande;
import entities.Facturation;
import repository.CommandeRepository;
import java.util.Date;
import java.util.List;

public class CommandeService {
    private CommandeRepository commandeRepository;
    private FacturationService facturationService;

    public CommandeService(CommandeRepository commandeRepository, FacturationService facturationService) {
        this.commandeRepository = commandeRepository;
        this.facturationService = facturationService;
    }

    // Permet de sauvegarder et de générer automatiquement la facture liée
    public void enregistrerCommande(Commande commande) {
        commandeRepository.save(commande);   // Génère l'ID automatique de la commande
        
        // Génération automatique de la facture après validation de la commande
        String numFacture = "FAC-" + commande.getNumero().substring(4);
        Facturation facture = new Facturation(0, numFacture, new Date(), commande);
        
        // Liaison bidirectionnelle
        commande.setFacture(facture);
        facturationService.ajouterFacture(facture);
    }

    public List<Commande> listerCommandes() {
        return commandeRepository.findAll();
    }
}
