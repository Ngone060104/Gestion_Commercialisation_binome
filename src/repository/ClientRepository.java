package repository;

import entities.Client;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {
    private List<Client> clients = new ArrayList<>();
    private int lastId = 0;

    public void save(Client client) {
        lastId++;
        client.setId(lastId);
        clients.add(client);
        

    }

    public List<Client> findAll() {
        return clients;
    }

    // BONUS : Recherche d'un client par son numéro de téléphone
    public Client findByTelephone(String telephone) {
        for (Client c : clients) {
            if (c.getTelephone().equals(telephone)) {
                return c;
            }
        }
        return null;
    }
}
