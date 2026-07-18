package service;

import entities.Produit;
import repository.ProduitRepository;
import java.util.List;

public class ProduitService {
    private ProduitRepository repository;

    // Constructeur sans paramètre
    public ProduitService() {
        this.repository = new ProduitRepository();
    }

    // Pour injection (si besoin)
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

    public Produit rechercherParLibelle(String libelle) {
        return repository.findByLibelle(libelle);
    }

    public boolean modifierQuantiteStock(int id, int nouvelleQte) {
        Produit p = repository.findById(id);
        if (p != null && nouvelleQte >= 0) {
            p.setQteStock(nouvelleQte);
            return true;
        }
        return false;
    }
}