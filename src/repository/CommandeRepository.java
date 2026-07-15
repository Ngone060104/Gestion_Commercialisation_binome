package repository;

import entities.Commande;
import java.util.ArrayList;
import java.util.List;

public class CommandeRepository {
    private List<Commande> commandes = new ArrayList<>();
    private int lastId = 0;

    public void save(Commande commande) {
        lastId++;
        commande.setId(lastId);
        commandes.add(commande);
        
    }

    public List<Commande> findAll() {
        return commandes;
    }
}
