package by.ganevich.service;

import by.ganevich.entity.Client;
import by.ganevich.repository.BankAccountRepository;
import by.ganevich.repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ClientService implements BaseService<Client> {

    private final ClientRepository clientRepository;
    private final BankAccountRepository bankAccountRepository;

    public void save(Client client) {
        log.info("ClientService: Save of client is called");
        clientRepository.save(client);
        log.info("Client " + client.getId() + " successfully created.");
    }

    public List<Client> readAll() {
        log.info("ClientService: Read all of clients is called.");
        List<Client> clients = clientRepository.findAll();
        log.info("The list of clients is successfully read.");
        return clients;
    }

    public Client findClientByName(String name) {
        log.info("ClientService: Find client by name is called");
        Client client = clientRepository.findByName(name);
        log.info("Client with name " + name + "is successfully found.");
        return client;
    }

    public void removeClient(Client client) {
        log.info("ClientService: Remove client is called");
        clientRepository.delete(client);
        log.info("Client " + client.getId()+ " successfully removed.");
    }

    public Optional<Client> findClientById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        log.info("Client " + id + " successfully found.");
        return client;
    }

    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
        log.info("Client wit id " + id + " successfully removed.");
    }

}
