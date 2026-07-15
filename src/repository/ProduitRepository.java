package repository;

import entities.Produit;
import java.util.ArrayList;
import java.util.List;

public class ProduitRepository {
    private List<Produit> produits = new ArrayList<>();
    private int lastId = 0;

    public void save(Produit produit) {
        lastId++;
        produit.setId(lastId);
        produits.add(produit);
        
    }

    public List<Produit> findAll() {
        return produits;
    }

    public Produit findById(int id) {
        for (Produit p : produits) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // BONUS : Recherche d'un produit par son libellé
    public Produit findByLibelle(String libelle) {
        for (Produit p : produits) {
            if (p.getLibelle().equalsIgnoreCase(libelle)) {
                return p;
            }
        }
        return null;
    }
}
