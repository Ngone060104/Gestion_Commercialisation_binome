package service;

import entities.Client;
import repository.ClientRepository;
import java.util.List;

public class ClientService {
    private ClientRepository repository;

    // Constructeur sans paramètre - le service crée son propre repository
    public ClientService() {
        this.repository = new ClientRepository();
    }

    // Gardez aussi l'autre constructeur pour les tests (optionnel)
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public void ajouterClient(Client client) {
        repository.save(client);
    }

    public List<Client> listerClients() {
        return repository.findAll();
    }

    public Client rechercherParTelephone(String telephone) {
        return repository.findByTelephone(telephone);
    }
}