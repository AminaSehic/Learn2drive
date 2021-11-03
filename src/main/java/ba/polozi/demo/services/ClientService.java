package ba.polozi.demo.services;

import ba.polozi.demo.models.Client;
import ba.polozi.demo.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {
    @Autowired
    private final ClientRepository clientRepository;

    public List<Client> findAll() {
        return (List<Client>) clientRepository.findAll();
    }

    public Client saveClient(Client c) {
        return clientRepository.save(c);
    }

    public Client findClientById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return client.get();
        } else {
            return null;
        }
    }

    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}