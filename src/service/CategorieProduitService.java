package service;

import entities.CategorieProduit;
import repository.CategorieProduitRepository;
import java.util.List;

public class CategorieProduitService {
    private CategorieProduitRepository repository;

    //  Constructeur sans paramètre
    public CategorieProduitService() {
        this.repository = new CategorieProduitRepository();
    }

    //  Pour injection (si besoin)
    public CategorieProduitService(CategorieProduitRepository repository) {
        this.repository = repository;
    }

    public void ajouterCategorie(CategorieProduit categorie) {
        repository.save(categorie);
    }

    public List<CategorieProduit> listerCategories() {
        return repository.findAll();
    }

    public CategorieProduit rechercherParId(int id) {
        return repository.findById(id);
    }
}