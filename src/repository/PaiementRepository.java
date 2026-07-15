package repository;

import entities.paiement;
import java.util.ArrayList;
import java.util.List;

public class PaiementRepository {
    private List<paiement> paiements = new ArrayList<>();
    private int lastId = 0;

    public void save(paiement p) {
        lastId++;
        p.setId(lastId);
        paiements.add(p);
        
    }

    public List<paiement> findAll() {
        return paiements;
    }
}
