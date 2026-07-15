package repository;

import entities.CategorieProduit;
import java.util.ArrayList;
import java.util.List;

public class CategorieProduitRepository {
    private List<CategorieProduit> categories = new ArrayList<>();
    private int lastId = 0; // Compteur interne

    public void save(CategorieProduit categorie) {
        lastId++;
        categorie.setId(lastId); // Assigne l'ID automatiquement
        categories.add(categorie);
        
    }

    public List<CategorieProduit> findAll() {
        return categories;
    }

    public CategorieProduit findById(int id) {
        for (CategorieProduit cat : categories) {
            if (cat.getId() == id) {
                return cat;
            }
        }
        return null;
    }
}
