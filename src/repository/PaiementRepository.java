package repository;

import entities.Paiement;
import java.util.ArrayList;
import java.util.List;

public class PaiementRepository {
    private List<Paiement> Paiements = new ArrayList<>();
    private int lastId = 0;

    public void save(Paiement p) {
        lastId++;
        p.setId(lastId);
        Paiements.add(p);
        
    }

    public List<Paiement> findAll() {
        return Paiements;
    }
}
