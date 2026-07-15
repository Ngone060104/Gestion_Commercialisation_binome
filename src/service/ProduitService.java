package service;

import entities.Produit;
import repository.ProduitRepository;
import java.util.List;

public class ProduitService {
    private ProduitRepository repository;

    public ProduitService(ProduitRepository repository) {
        this.repository = repository;
    }

    public void ajouterProduit(Produit produit) {
        repository.save(produit);
    }

    public List<Produit> listerProduits() {
        return repository.findAll();
    }

    public Produit rechercherParId(int id) {
        return repository.findById(id);
    }

    // BONUS : Recherche d'un produit par son libellé
    public Produit rechercherParLibelle(String libelle) {
        return repository.findByLibelle(libelle);
    }

    // BONUS & EXIGENCE : Mettre à jour la quantité en stock d'un produit
    public boolean modifierQuantiteStock(int id, int nouvelleQte) {
        Produit p = repository.findById(id);
        if (p != null && nouvelleQte >= 0) {
            p.setQteStock(nouvelleQte); // Le setter gère automatiquement le statut disponible/rupture
            return true;
        }
        return false;
    }
}
