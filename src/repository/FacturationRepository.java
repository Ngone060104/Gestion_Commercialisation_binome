package repository;

import entities.Facturation;
import java.util.ArrayList;
import java.util.List;

public class FacturationRepository {
    private List<Facturation> factures = new ArrayList<>();
    private int lastId = 0;

    public void save(Facturation facture) {
        lastId++;
        facture.setId(lastId);
        factures.add(facture);
        
    }

    public List<Facturation> findAll() {
        return factures;
    }

    public Facturation findById(int id) {
        for (Facturation f : factures) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }
}
